package demo

import japgolly.scalajs.react.{Callback, ScalaFnComponent}
import japgolly.scalajs.react.vdom.html_<^._
import org.scalablytyped.runtime.StringDictionary
import typings.antd.antdStrings
import typings.antd.components._
import typings.antd.formFormMod.useForm
import typings.antd.gridColMod.ColProps
import typings.rcFieldForm.interfaceMod.BaseRule
import typings.std.global.console

import scala.scalajs.js

object CoordinatedDemo {
  val component = ScalaFnComponent[String] { noteTitle =>
    val form = useForm().head
    Form
      .form(form)
      .labelCol(ColProps().setSpan(5))
      .wrapperCol(ColProps().setSpan(12))
      .onFinish(store => Callback(console.log("Received values of form: ", store)))(
        FormItem
          .label(noteTitle)
          .name("note")
          .rules(js.Array(BaseRule().setRequired(true).setMessage("Please input your note!")))(
            Input()
          ),
        FormItem
          .label("Gender")
          .name("gender")
          .rules(js.Array(BaseRule().setRequired(true).setMessage("Please select your gender!'")))(
            Select[String]
              .placeholder("Select a option and change input text above")
              .onChange { (value, _) =>
                Callback(
                  form.setFieldsValue(
                    StringDictionary(
                      "gender" -> value,
                      "note" -> s"Hi, ${if (value == "male") "man" else "lady"}!"
                    )
                  )
                )
              }(
                Option(value = "male")("Male"),
                Option(value = "female")("Female")
              )
          ),
        FormItem.wrapperCol(ColProps().setSpan(12).setOffset(5))(
          Button.`type`(antdStrings.primary).htmlType(antdStrings.submit)("Submit")
        )
      )
  }
}
