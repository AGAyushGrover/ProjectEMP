package App.model

import App.action.EmployeeRoutes
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.Materializer

import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.io.StdIn
import scala.util.{Failure, Success}

case class Employee(empID : Int,
                    education: String,
                    joiningYear : Int,
                    city : String,
                    paymentTier : Int,
                    age : Int,
                    gender : String,
                    everBenched : String,
                    experienceInCurrentDomain : Int,
                    leaveOrNot : Int
                   )


object Application extends App {



  implicit val system: ActorSystem = ActorSystem("employeeSystem")
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  implicit val materializer: Materializer = Materializer(system)

  val employeeService = new EmployeeService()
  employeeService.loadData("/Users/ayushgrover/IdeaProjects/ProjectEMP/src/main/scala/App/model/EmployeeData.csv")

  val routess = new EmployeeRoutes(employeeService)
  val bindingFuture = Http().newServerAt("localhost", 8080).bindFlow(routess.routes)
  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine()

  bindingFuture
    .flatMap(_.unbind())
    .onComplete {
      case Success(_) =>
        system.terminate()
      case Failure(e) =>
        println(s"Failed to unbind and terminate: ${e.getMessage}")
        system.terminate()
    }
}

