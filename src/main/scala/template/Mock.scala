package template

import java.io.{File, FilenameFilter}
import scala.io.Source
import play.api.libs.json.{Json, JsValue}

object Mock extends Object {
  val mockDirPath: String = "src/mock"
  val mockDir: File = new File(mockDirPath)

  val mockPathMap: Map[String, List[String]] = (() => {
    // mockディレクトリ以下のjsonファイルのみを抽出
    val files = mockDir.listFiles()
    var mockPathMap = Map[String, List[String]]()
    files.foreach((file) => {
      if (file.isDirectory()) {
        mockPathMap =
          mockPathMap + (file.getName() -> file
            .listFiles(new FilenameFilter {
              override def accept(dir: File, name: String): Boolean =
                name.endsWith(".json")
            })
            .toList.map(file => file.getPath()))
      }
    })
    mockPathMap
  })()


  def withMock(name: String, callback: (JsValue, String) => Unit): Unit = {
    mockPathMap.get(name).get.foreach((mockPath) => {
      val source: String = Source.fromFile(mockPath, "utf-8").getLines.mkString
      val json: JsValue = Json.parse(source)
      callback(json, mockPath)
    })
  }
}
