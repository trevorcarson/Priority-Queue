/*A class for storing data in a Priority Queue*/

public class PriorityNode{
   private int priority;   //the priority of this data, bigger = higher
   private Object data;    //the data to be store in the queue
   
   public PriorityNode(int priority, Object data){
      /*PriorityNode constructor*/
      this.priority = priority;
      this.data = data;
   }
   
   public String toString(){
      /*Return a String representation of this node*/
      return this.priority + "-" + data;
   }
   
   public int getPriority(){
      /*Return the priority of this node*/
      return this.priority;
   }
   
   public Object getData(){
      /*Return the data store in this node*/
      return this.data;
   }
}