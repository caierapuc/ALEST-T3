import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Entities.Barbarian;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        BarbarianTree tree = new BarbarianTree();

        try{
            System.out.println("Insira o caminho para o arquivo do pergaminho a ser analisado:");
            String path = sc.nextLine();//"/Users/caiera/Desktop/PUCRS/ALEST I/ALEST-T3/teste.txt";

            while (path.length() <= 0){
                System.out.print("O caminho para o pergaminho é obrigatório, tente novamente:");
                path = sc.nextLine();
            }

            File file = new File(path);
            
            if (!file.exists()){
                sc.close();
                throw new FileNotFoundException();
            }

            try (Scanner fr = new Scanner(file)){
                int count = 0;
                long firstLine = 0;
                while(fr.hasNextLine()){
                    if (count > 1){
                        String[] line = fr.nextLine().split(" ");
                        tree.put(line[0], new Barbarian(line[1], Float.parseFloat(line[2])));
                    }
                    else if (count == 0){
                        firstLine = Long.parseLong(fr.nextLine());
                        count++;
                    }
                    else if (count == 1){
                        String[] line = fr.nextLine().split(" ");
                        tree.putFirst(new Barbarian(line[0], firstLine));
                        tree.put(line[0], new Barbarian(line[1], Float.parseFloat(line[2])));
                        count++;
                    }
                }

                fr.close();
            }

            sc.close();

            var familia = tree.toList();

            System.out.println("Processando...");

            Barbarian aux = null;
            for (var obj: familia){
                if (aux == null)
                aux = obj;
                
                if (obj.getHeritage() > aux.getHeritage() && obj.getGeneration() >= aux.getGeneration())
                aux = obj;
                
                if (obj.getGeneration() > aux.getGeneration())
                aux = obj;
            }
            
            System.out.println("O(s) herdeiro(s) com mais terras é(são): ");
            float heritage = aux.getHeritage();
            familia.stream().filter(x -> x.getHeritage() == heritage).forEach(x -> System.out.println(x.getName()));

        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
        }

    }
}