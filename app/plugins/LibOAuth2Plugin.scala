package plugins

import play.api._

class LibOAuth2Plugin(app: Application) extends Plugin {
    val name = "oauth2-lib"
    override def onStart() {
        Logger.debug("--- " + name + " start!")
    }
    override def onStop() {
        Logger.debug("--- " + name + " stop!")
    }
}