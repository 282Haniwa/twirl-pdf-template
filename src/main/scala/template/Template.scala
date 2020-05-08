package template

object Template extends Object {
  val templateList = Map(
    // "ターゲット名" -> テンプレートにデータを入れて返す関数
    // 例 : ディレクトリ名が`target`でテンプレートがindex.scala.htmlの場合
    // "target" -> (data => template.target.html.index(data))
    "sample" -> (data => template.sample.html.index(data)),
  )

  val getTemplate = name => templateList(name)
}
