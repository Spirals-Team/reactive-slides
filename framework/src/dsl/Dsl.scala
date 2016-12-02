package dsl

import scalatags.Text.all.{attr => _, _}
import scalatags.Text.tags2._

object Dsl {
  def presentationTitle = "Présentation exemple avec reveal.js"

  def linkURL(content: String) =
    attr("href"):=content

  def italic(content: String) =
    em(content)

  def bold(content: String) =
    strong(content)

  def emptyLine =
    br

  def source(content: String) =
    attr("src"):=content

  def codeQuote(content: String) =
    pre(
      code(
        attr("data-trim"):="data-trim",
        attr("data-noescape"):="data-noescape",
        content
      )
    )
}
