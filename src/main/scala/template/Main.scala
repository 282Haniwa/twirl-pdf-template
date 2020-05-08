package template

import java.io.{File, ByteArrayInputStream}
import scala.sys.process._

object Main extends App {
  val templateDirPath: String = "src/main/twirl/template"
  val templateDir: File = new File(templateDirPath)
  val destDirPath: String = "dest"
  val destDir: File = new File(destDirPath)
  if (destDir.exists()) {
    destDir
      .listFiles()
      .foreach((file) => {
        file.delete()
      })
  } else {
    destDir.mkdir()
  }

  templateDir
    .listFiles()
    .foreach((target) => {
      if (target.isDirectory()) {
        val targetName: String = target.getName()
        // println(s"---${targetName}---")
        Mock.withMock(
          targetName,
          (json, mockPath) => {
            println(s"Print ${targetName} with ${mockPath} to ${destDirPath}/${json("name").as[String]}")
            // println(s"---${mockPath}---")
            val html = Template.getTemplate(targetName)(json("data"))
            // println(html)
            val inputStream =
              new ByteArrayInputStream(html.toString().getBytes("UTF-8"))
            Process(s"${json("command").as[String]} --quiet - ${destDirPath}/${json("name").as[String]}")
              .#<(inputStream)
              .run()
          }
        )
      }
    })
}
