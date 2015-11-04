package mysolutions;

import java.util.ArrayList;
import java.util.List;

public class Mnemonics {

	char[][] assignments = new char[][] { null, // /0
			null, // 1
			{ 'A', 'B', 'C' }, // 2
			{ 'D', 'E', 'F' }, // 3
			{ 'G', 'H', 'I' }, // 4
			{ 'J', 'K', 'L' }, // 5
			{ 'M', 'N', 'O' }, // 6
			{ 'P', 'Q', 'R', 'S' }, // 7
			{ 'T', 'U', 'V' }, // 8
			{ 'W', 'X', 'Y', 'Z' } // 9
	};

	public List<String> generateMenmonicsRecur(String number) {
		List<String> output = new ArrayList<>();
		if (number.length() == 0) {
			return output;
		}
		List<String> result = generateMenmonicsRecur(number.substring(1));
		char[] letters = assignments[number.charAt(0) - 48];
		for (int i = 0; i < letters.length; i++) {
			if (result.size() > 0) {
				for (int j = 0; j < result.size(); j++) {
					output.add(letters[i] + result.get(j));
				}
			} else {
				output.add(String.valueOf(letters[i]));
			}
		}
		return output;
	}

	public List<String> generateMenmonicsIter(String number) {
		List<String> output = new ArrayList<>();
		char[] chars = number.toCharArray();
		for (int i = chars.length - 1; i >= 0; i--) {
			output = add(output, chars[i]);
		}
		return output;
	}
	
	private List<String> add(List<String> output, char c) {
		char[] letters = assignments[c-48];
		List<String> temp = new ArrayList<>();
		if ( output==null||output.size()==0) {
			for(int i=0;i<letters.length;i++) {
				temp.add(String.valueOf(letters[i]));
			}
		} else {
			for(int i=0;i<letters.length;i++) {
				for (int j = 0; j < output.size(); j++) {
					temp.add(letters[i] + output.get(j));
				}
			}			
		}
		return temp;
	}
	
	static boolean isSame(List<String> list1, List<String> list2) {
		return list1.containsAll(list2) && list2.containsAll(list1);
	}

	public static void main(String[] args) {
		Mnemonics m = new Mnemonics();
		String number = "357";
		List<String> output1 = m.generateMenmonicsRecur(number);
		List<String> output2 = m.generateMenmonicsIter(number);
		for (String s : output1) {
			System.out.println(s);
		}
		System.out.println("***********");
		for (String s : output2) {
			System.out.println(s);
		}
		
		System.out.println("Same? "+isSame(output1,output2));
	}
}
