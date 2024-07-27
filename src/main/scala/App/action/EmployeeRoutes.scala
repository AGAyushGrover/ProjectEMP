package App.action

import App.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import spray.json.DefaultJsonProtocol.{IntJsonFormat, StringJsonFormat, jsonFormat10}
import spray.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import org.json4s.native.Json

class EmployeeRoutes(service: EmployeeService) extends DefaultJsonProtocol {

  implicit val employeeFormat: RootJsonFormat[Employee] = jsonFormat10(Employee)

  val routes: Route = pathPrefix("employees") {
    concat(
      post {
        entity(as[Employee]) { employee =>
          service.add(employee) match {
            case true => complete(StatusCodes.Created -> "Employee added")
            case false => complete(StatusCodes.NotAcceptable -> "Employee with same empId already exists")
          }
        }
      },
      pathEndOrSingleSlash {
        get {
          complete(StatusCodes.OK, service.getAll)
        }
      },
         path(IntNumber) { empID =>
        get {
          service.find(empID) match {
            case Some(employee) => complete(StatusCodes.OK, employee)
            case None => complete(StatusCodes.NotFound, s"Employee with ID $empID not found")
          }
        } ~
          delete {
            service.delete1(empID) match {
              case true => complete(StatusCodes.OK , s"Employee deleted")
              case false => complete(StatusCodes.NotFound, s"Employee not found")
            }
          }
      } ~ put {
          entity(as[Employee]) { employee =>
            service.update(employee)
            complete(StatusCodes.OK -> "Person updated successfully.")
          }
        }
    )
  }
}
