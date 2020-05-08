# twirl-pdf-template

scalaでtwirlとwkhtmltopdfを使ってpdfを生成するテンプレートです。

`*.scala.html`のテンプレートファイルと受け渡すデータ(`json`ファイル)を書くだけで、静的なpdfファイルを生成することができます。

## 環境構築

```sh
git clone https://github.com/282Haniwa/twirl-pdf-template.git
cd twirl-pdf-template

# dockerコンテナの作成
cd docker
docker image build -t twirl-pdf .
docker run -i -t -d -v [共有したいディレクトリ]:[コンテナの共有する先のディレクトリ] --name pdf-print twirl-pdf
# コンテナの中に入る
docker exec -it pdf-print /bin/bash

# コンテナの中でプロジェクトのディレクトリに入りsbtシェルの起動
cd [プロジェクトまでのパス]/twirl-pdf-template
sbt

# sbtシェルの中でプログラムの実行
run
```

※プロジェクトのディレクトリにアクセスできるように共有ファイルを設定してください。


## テンプレート実装プロセス

1. テンプレートファイルを作る
1. mockデータファイルを作る
1. テンプレートに登録する
1. 実装する

### 1.テンプレートファイルを作る

テンプレートファイル(`*.scala.html`)を`src/main/twirl/template/[ディレクトリ]/`以下に作成する。

`[ディレクトリ]`はmockデータのディレクトリ名と同じにする。

### 2.mockデータファイルを作る

mockデータファイル(`json`)を`src/mock/[ディレクトリ]/`以下に作成する。

`[ディレクトリ]`はテンプレートファイルのディレクトリ名と同じにする。

### 3.テンプレートに登録する

`src/main/scala/template/Template.scala`に他のテンプレートと同じように登録する

### 4.実装する

#### mockの実装

mockデータの`json`とテンプレートファイルを実装していく。

mockデータのフィールドの例：

```json
{
  "name": "test1_data1.pdf",
  "command": "wkhtmltopdf",
  "data": {}
}
```

`name` : 出力されるpdfのファイル名

`command` : テンプレート作成時に実行されるコマンド(コマンドの末尾に保存するファイル名が指定される)

`data` : テンプレートに渡されるデータのオブジェクト



#### テンプレートの実装

テンプレートファイルの実装は以下のリンクを参照

dataオブジェクトのアクセス方法([play-json](https://github.com/playframework/play-json)のライブラリを使っているので、以下のドキュメントを参照)

https://www.playframework.com/documentation/2.7.x/ScalaJson

基本的にオブジェクトのキーで`data("hoge")("huga")`のように参照できる。

配列などは`data("array").as[Array[String]]`のように型を`Array[String]`に変換することで参照できる。



テンプレートエンジンは[twirl](https://github.com/playframework/twirl)

以下のドキュメントを参照

https://www.playframework.com/documentation/2.8.x/ScalaTemplates
