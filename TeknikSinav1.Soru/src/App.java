/* bu program kullanıcıdan input alıp x ve y eksenine paralel doğruların sayısını ekrana basar */

import java.util.*;

public class App {

    static List<List<List<Integer>>> lines = new ArrayList<>(); // x veya y eksenine paralel doğruların listesi

    /*
     * doğru oluşturan nokta çiftlerini(ikişer elemanlı liste çiftleri)
     * lines listesine ekliyor
     */
    public static void addLineToList(int index, List<Integer> pointsOfI, List<Integer> pointsOfJ) {
        lines.add(new ArrayList<>());
        lines.get(index).add(new ArrayList<>());
        lines.get(index).add(new ArrayList<>());
        lines.get(index).get(0).addAll(pointsOfI);
        lines.get(index).get(1).addAll(pointsOfJ);
    }

    // eklenmek istenen doğru, lines listesinde zaten var mı diye kontrol ediyor
    public static boolean canAddLineToList(int index, List<Integer> pointsOfI, List<Integer> pointsOfJ) {
        for (List<List<Integer>> list : lines) {
            if (list.contains(pointsOfI) && list.contains(pointsOfJ)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        List<List<Integer>> points = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();

        // kullanıcıdan istenen formata uygun input alıyor
        Scanner scan = new Scanner(System.in);
        System.out.println("Örnek: (1,2), (3,7), (0,6)");
        System.out.println("Lütfen noktaları yukarıdaki örnekte olduğu gibi giriniz: ");
        String input = scan.nextLine();
        scan.close();

        // inputtaki sayıları numbers listesine ekliyor
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                numbers.add(Character.getNumericValue((input.charAt(i))));
            }
        }

        /*
         * numbers listesindeki sayıları, sayı çiftleri(ardışık 2 elemanlı listeler)
         * şeklinde points listesine ekliyor
         */
        int indexOfPointList = 0;
        for (int i = 0; i < numbers.size(); i += 2) {
            points.add(new ArrayList<>());
            points.get(indexOfPointList).add(numbers.get(i));
            points.get(indexOfPointList).add(numbers.get(i + 1));
            indexOfPointList++;
        }

        int numOflinesParallelToXAxis = 0;
        int numOfLinesParallelToYAxis = 0;
        int indexOfLineList = 0;
        // listedeki bütün nokta kombinasyonlarına bakıyor
        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                int firstElementOfPointListI = points.get(i).get(0);
                int secondElementOfPointListI = points.get(i).get(1);
                int firstElementOfPointListJ = points.get(j).get(0);
                int secondElementOfPointListJ = points.get(j).get(1);

                /*
                 * y eksenine paralel doğruları lines listesine ekliyor ve o doğruların sayısını
                 * arttırıyor
                 */
                if (firstElementOfPointListI == firstElementOfPointListJ &&
                        secondElementOfPointListI != secondElementOfPointListJ) {
                    if (canAddLineToList(indexOfLineList, points.get(i), points.get(j))) {
                        addLineToList(indexOfLineList, points.get(i), points.get(j));
                        numOfLinesParallelToYAxis++;
                        indexOfLineList++;
                    }
                }
                /*
                 * x eksenine paralel doğruları lines listesine ekliyor ve o doğruların sayısını
                 * arttırıyor
                 */
                else if (secondElementOfPointListI == secondElementOfPointListJ &&
                        firstElementOfPointListI != firstElementOfPointListJ) {
                    if (canAddLineToList(indexOfLineList, points.get(i), points.get(j))) {
                        addLineToList(indexOfLineList, points.get(i), points.get(j));
                        numOflinesParallelToXAxis++;
                        indexOfLineList++;
                    }
                }
            }
        }

        // x ve y eksenine paralel doğru sayısını basıyor
        System.out.println("x eksenine paralel doğru sayısı: " + numOflinesParallelToXAxis);
        System.out.println("y eksenine paralel doğru sayısı: " + numOfLinesParallelToYAxis);
    }
}
