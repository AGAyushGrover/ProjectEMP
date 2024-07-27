import App.model.Employee
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.Route

import scala.collection.mutable.ListBuffer



trait DataValidation {
  val csvFile = "/Users/ayushgrover/IdeaProjects/Tutorial/src/Project/model/EmployeeData.csv"

  def readEmployees(csvFile: String): ListBuffer[Employee] = {
    val lines = scala.io.Source.fromFile(csvFile).getLines().drop(1) // Skip header
    val buffer = ListBuffer[Employee]()
    lines.foreach { line =>
      val Array(empID, education, joiningYear, city, paymentTier, age, gender, everBenched, experienceInCurrentDomain, leaveOrNot) = line.split(",")
      buffer += Employee(empID.toInt,
        education,
        joiningYear.toInt,
        city,
        paymentTier.toInt,
        age.toInt,
        gender,
        everBenched,
        experienceInCurrentDomain.toInt,
        leaveOrNot.toInt)
    }
    buffer
  }
}
//  val employees = readEmployees(csvFile)


//  def standarlisedInputs(employee: Employee): Route = {
//    Employees += employee
//    complete(StatusCodes.OK -> "Success.")
//  }
//}
//  Employees.foreach(println)
//  def preprocess(EList : List[Employee]) : List[Employee] = {
//    EList.filter(isValid).map(standarlisedInputs)
//  }

//    def isValid(employee : Employee) : Option[String] = {
//      val s1 :String= "[a-zA-Z]+"
//      employee match {
//        case Employee(empId,_,_,_,_,_,_,_,_,_) if empId < 0 =>
//          Some("empId should be greater than zero.")
//        case Employee(_,education,_,_,_,_,_,_,_,_) if !education.nonEmpty =>
//          Some("empId should be greater than zero.")
//        case Employee(_,_,joiningYear,_,_,_,_,_,_,_) if joiningYear > 0 =>
//          Some("empId should be greater than zero.")
//        case Employee(_,_,_,_,city,_,_,_,_,_) if city.matches(s1) =>
//          Some("empId should be greater than zero.")
//        case Employee(_,_,_,_,paymentTier,_,_,_,_,_) =>
//          Some("empId should be greater than zero.")
//        case Employee(_,_,_,_,_,age,_,_,_,_)=>
//          Some("empId should be greater than zero.")
//        case Employee(_,_,_,_,_,_,gender,_,_,_)=>
//          Some("empId should be greater than zero.")
//        case Employee(_,_,_,_,_,_,_,everBenched,_,_)=>
//          Some("empId should be greater than zero.")
//        case Employee(_,_,_,_,_,_,_,_,experienceInCurrentDomain,_)=>
//          Some("empId should be greater than zero.")
//        case Employee(_,_,_,_,_,_,_,_,_,leaveOrNot)=>
//          Some("empId should be greater than zero.")
//
//      }

// def standarlisedInputs(employee : Employee) : Route = {
//   isValid(employee) match {
//     case Some(errorMsg) =>
//       complete(StatusCodes.BadRequest -> errorMsg)
//     case None =>
//       Employees += employee
//       complete(StatusCodes.OK -> "Success.")
//   }
// }
