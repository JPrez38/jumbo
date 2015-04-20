import scala.io.Source
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.StringBuilder
import scala.io.StdIn

object jumbo {
	val dictionary_file = "words"
	

	def main(args: Array[String]) : Unit = {
		val dictionary = build_dictionary(dictionary_file)
		val list = if (args.length > 0) {
			 solve_jumbo(args(0),dictionary)
		} else {
			println("Please enter a string: ")
			solve_jumbo(StdIn.readLine(),dictionary)
		}
		list.foreach(x => println(x))

	}

	def solve_jumbo(input_string: String,dictionary: HashMap[StringBuilder,String]) : ListBuffer[String] = {
		var jumbo_list = new ListBuffer[String]()
		var perms = new ListBuffer[String]()
		/* code works by generating all permutations, then finding the combinations */
		permute_string(input_string.toCharArray,0)
		perms.foreach(x => combine_strings(x,new StringBuilder(),0))

		def permute_string(in_str: Array[Char], index: Int) : Unit = {
			if (in_str.length == index) {
				val str = in_str.mkString
				if (!perms.contains(str)) perms += str
				return
			} 
			for( i <- index until in_str.length) {
				val tmp = in_str(index)
				in_str(index) = in_str(i)
				in_str(i) = tmp
				permute_string(in_str,index + 1)
				in_str(i) = in_str(index)
				in_str(index) = tmp
			} 

		}

		def combine_strings(in_str: String, out_str: StringBuilder,index: Int) : Unit = {
			for (i <- index until in_str.length) {
				out_str.append(in_str.charAt(i))
				if (dictionary.contains(out_str)) { 
					val x = out_str.toString
					if (!jumbo_list.contains(x)) jumbo_list += x
				}
				combine_strings(in_str,out_str,i+1)
				out_str.deleteCharAt(out_str.length-1)
			}
		}
		jumbo_list
	} 

	def build_dictionary(file: String) : HashMap[StringBuilder,String] = {
		var dictionary = HashMap[StringBuilder, String]() //Use hash table for O(1) lookup
		Source.fromFile(dictionary_file).getLines().foreach { 
			line=>
				if (line.length != 1) { /* single characters should not count as words */
					dictionary += (new StringBuilder(line) -> "")
				}
		}
		println("Dictionary size equals " + dictionary.size) // from the mac words dictionary
		dictionary
	}
}