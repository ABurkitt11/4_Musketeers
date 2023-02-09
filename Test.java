import java.util.ArrayList;

public class Test {
    public static void testClass(ArrayList<Item> dataStorage, DynamicTable dynamicTable) {

        for(int i = 0; i < dynamicTable.getItemAmount()-1; i++)
        {
            for(int w = 0; w < dynamicTable.getCapacity(); w++)
            {
                //v[i,w] = max(V[i-1,w], Vi + V[i-1,w-wi]))
                //Math.max(dynamicTable[i-1,j])
//                Math.max(dynamicTable.DTable[Math.max(i-1, dynamicTable.getCapacity()])][j],dynamicTable.DTable[i][j] + dynamicTable.DTable[i-1, );

 /*

                if Wi <= w
                 - if weight is too big for knapsack

                    if bi + B[i-1, w-wi] > B[i - w-1]
                        B[i, w] = bi + B[i-1, w-wi]
                    else
                        B[i,w] = B[i-1, w]
                else
                    B[i,w] = B[i-1,w]


if(dataStorage.get(i).getPrice() + dynamicTable.DTable[Math.max(i-1, 0)][Math.min(dynamicTable.getCapacity()-dataStorage.get(i-1).getWeight(), dynamicTable.DTable.length)] > dynamicTable.DTable[Math.max(i-1, 0)][w]  )
                    {
                        dynamicTable.DTable[i][w] = dataStorage.get(i-1).getPrice() + dynamicTable.DTable[Math.max(i-1, 0)][Math.min(dynamicTable.getCapacity()-dataStorage.get(i-1).getWeight(), dynamicTable.DTable.length)];
                    }
                    else
                    {
                        dynamicTable.DTable[i][w] = dynamicTable.DTable[Math.max(i-1,0)][w];
                    }



                 */

                //figure out wi
                if (dataStorage.get(Math.max(i-1, 0)).getWeight() <= dynamicTable.getCapacity())
                {
                    if(dataStorage.get(i).getPrice() + dynamicTable.DTable[Math.max(i-1, 0)][Math.min(dynamicTable.getCapacity()-dataStorage.get(i-1).getWeight(), dynamicTable.DTable.length)] > dynamicTable.DTable[Math.max(i-1, 0)][w]  )
                    {
                        dynamicTable.DTable[i][w] = dataStorage.get(i-1).getPrice() + dynamicTable.DTable[Math.max(i-1, 0)][Math.min(dynamicTable.getCapacity()-dataStorage.get(i-1).getWeight(), dynamicTable.DTable.length)];
                    }
                    else
                    {
                        dynamicTable.DTable[i][w] = dynamicTable.DTable[Math.max(i-1,0)][w];
                    }
                }
                else
                {
                    dynamicTable.DTable[i][w] = dynamicTable.DTable[Math.max(i-1,0)][w];
                }



            }
        }


        for(int x = 0; x < dynamicTable.DTable.length; x++)
        {
            for(int y = 0; y < dynamicTable.DTable[0].length; y++)
            {
                System.out.print(" "+dynamicTable.DTable[x][y]+" ");
            }
            System.out.println();
        }


    }
}
