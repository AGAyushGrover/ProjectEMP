//package App.model
//
//class EmployeeService(EList : List[Employee]) {
//
//  def find(EList : List[Employee], str : Int) : List[Employee] = {
//    EList.filter(_.empID == str)
//  }
//
//  def add(newemp : Employee, EList : List[Employee]) : List[Employee] = {
//    EList :+ newemp
//  }
//
//  def upd(updatedemp : Employee, EList : List[Employee]) : List[Employee] = {
//    EList.map ( emp =>
//      if(emp.empID == updatedemp.empID) updatedemp else emp
//    )
//  }
//
//  def del(id : Int, EList : List[Employee]) : List[Employee] = {
//    EList.filterNot(_.empID == id)
//  }
//}

package App.model

import java.io.{BufferedWriter, FileWriter}
import scala.collection.mutable.ListBuffer

class EmployeeService() {
  val buffer = ListBuffer[Employee]()

  def loadData(csvFile: String): ListBuffer[Employee] = {
      val lines = scala.io.Source.fromFile(csvFile).getLines().drop(1)
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

  def find(empID: Int): Option[Employee] = {
    buffer.find(_.empID == empID)
  }

  def add(newEmp: Employee): Boolean = {
    buffer.find(emp => emp.empID == newEmp.empID) match {
      case Some(_) => false
      case None =>
        buffer += newEmp
        true
    }
  }

  def update(updatedEmp: Employee): ListBuffer[Employee] = {
//    buffer.map(emp =>
//      if (emp.empID == updatedEmp.empID) updatedEmp else emp
//    )
    val emp = buffer.find(x => x.empID == updatedEmp.empID)
    emp match {
      case Some(e) =>
        buffer -= e
        buffer += updatedEmp
      case None => buffer += updatedEmp
    }
  }

  def delete1(empID: Int): Boolean = {
    val emp = buffer.find(x => x.empID == empID)
    emp match {
      case Some(e) => buffer -= e
        true
      case None => buffer
        false
    }
  }

  def getAll: List[Employee] = {
    buffer.toList
  }
}
