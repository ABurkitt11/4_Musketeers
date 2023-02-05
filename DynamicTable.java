public class DynamicTable 
{
 private int capacity;
 private int itemAmount;
 
 public DynamicTable(int newCapacity, int newItemAmount) 
 {
   itemAmount = newItemAmount;
   capacity = newCapacity;
 }
 
 
 public void setCapacity(int newCapacity) 
 {
  capacity = newCapacity;
 }
 
 public int getCapacity() {
  return capacity;
 }
 
 public void setItemAmount(int newItemAmount) 
 {
  itemAmount = newItemAmount;
 }
 
}
 
