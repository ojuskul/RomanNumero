object BasicRomanTest {

  def main(args: Array[String]): Unit = {
    var roman = Roman(100)
    println(roman.toString)

    roman = Roman("MI")
    println(roman.toString)

    //throws illegal argument error
    /*
    roman = Roman("XYZ")
    println(roman.toString)
    */
  }
}
