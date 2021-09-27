import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
public class Aliases {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Scanner file = null;
		while(file == null) {
			try {
				System.out.print("Enter file name(Pathway): ");
				String input = in.nextLine();
				file = new Scanner(new File(input));
			}
			catch(FileNotFoundException e){
				System.out.println("Invalid file");
			}
		}	
		List<Set<String>> sets = new ArrayList<>();
		while(file.hasNextLine()) {
			String line = file.nextLine();
			String[] strLine = line.split(":");
			String name1 = strLine[0].trim();
			String name2 = strLine[1].trim();
			List<Set<String>> changed = new ArrayList<>();
			for(Set<String> element: sets) {
				if(element.contains(name1)) {
					element.add(name2);
					changed.add(element);
				}
				else if(element.contains(name2)) {
					element.add(name1);
					changed.add(element);
				}
			}
			if(changed.size() == 0) {
				TreeSet<String> tree = new TreeSet<String>();
				tree.add(name1);
				tree.add(name2);
				sets.add(tree);
			}
			else {
				Set<String> result = changed.get(0);
				for(int i = 1; i < changed.size(); ++i) {
					result.addAll(changed.get(i));
					sets.remove(changed.get(i));
				}
			}
		}
		file.close();
		System.out.println("\nAliases\n");
		int index = 1;		
		for(Set<String> set: sets) {
			System.out.print(index + ". ");
			boolean first = true;
			for(String name: set) {
				if(first == true) {
					first = false;				
				}
				else {
					System.out.print(" AKA ");					
				}			
				System.out.print(name);			
			}
			++index;
			System.out.println();			
		}
	}
}
