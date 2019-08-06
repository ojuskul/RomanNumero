class Roman(var value: String, val compress: Boolean = false){

  //Utility structures for faster conversions
  private val decimalToRoman: Map[Int,Char] = Map[Int,Char](1000 -> 'M', 500 -> 'D', 100 -> 'C', 50 -> 'L', 10 -> 'X', 5 -> 'V', 1 -> 'I')
  private val romanTODecimal: Map[Char,Int] = Map[Char,Int]('I' -> 1, 'V' -> 5, 'X' -> 10, 'L' -> 50, 'C' -> 100, 'D' -> 500, 'M' -> 1000)
  val decimalKeyIterator = decimalToRoman.keysIterator.toList.sortWith(_ >= _)

  //This ensures that the string value is a valid roman numeral. Currently no checks for compressed romans present.
  require(isValid(value), "Invalid Roman Numeral")

  //toString method for class
  override def toString: String = this.value

  //Integer conversion
  def toInt: Int = {
    value.foldLeft(0)((accum,curr) =>{
      accum + romanTODecimal.get(curr).getOrElse(0)
    })
  }

  //Long conversion
  def toLong: Long = {
    this.toInt.toLong
  }

  //Integer to roman conversion
  def intToRomanString(input: Int): String = {
    var curr: Int = input
    var currRomanString: String = ""

    var currRomanIdx: Int = 0

    while(curr > 0 ){
      if(decimalKeyIterator(currRomanIdx) <= curr){
        currRomanString += decimalToRoman(decimalKeyIterator(currRomanIdx))
        curr -= decimalKeyIterator(currRomanIdx)
      }else{
        currRomanIdx += 1
      }
    }
    currRomanString
  }

  //Method to check if the roman value is valid. Currently doesn't check for compression
  def isValid(ip: String): Boolean = {
    !(s"""[^${romanTODecimal.keySet.mkString}]""".r.findAllIn(ip).length > 0)
  }
}

//Companion objects to create new instances
object Roman {
  def apply(input: Int): Roman = {
    var roman = new Roman("")
    roman.value = roman.intToRomanString(input)
    roman
  }

  def apply(input: Long): Roman = {
    apply(input.toLong)
  }

  def apply(input: String): Roman = {
    new Roman(input)
  }
}


