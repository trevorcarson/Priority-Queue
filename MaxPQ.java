/*Implement a Priority Queue using a Max-Heap and an array under the hood*/

public class MaxPQ{
   private PriorityNode[] heap;  //storage to hold the nodes on the heap
   private int size;             //the number of items presently on the heap
   
   private static int initCapacity = 4; //the initial capacity of the heap
   
   public MaxPQ(){
      /*Constructor for Max Priority Queues*/
      this.heap = new PriorityNode[initCapacity];
      this.size = 0;
   }
   
   private void resize(){
      /*Double the capacity of the current heap*/
      PriorityNode[] temp = this.heap; //hold the current array so we don't lose it
      this.heap = new PriorityNode[temp.length*2]; //double capacity
      //copy over the old values
      for(int i = 1; i <= this.size; i++){
         this.heap[i] = temp[i];
      }
   }
   
   public String toString(){
      /*Return a String representing this MaxPQ. Shows the internal storage as-is
      (i.e. not sorted strictly by priority)
      */
      String s = "[";
      for(int i = 0; i <= this.size; i++){
         if(i > 0){
            s += ", ";
         }
         if(this.heap[i] == null){
            s += "null";
         }
         else{
            s += this.heap[i].toString();
         }
      }
      return s + "]";
   }
   
   public void simpleAdd(int priority, Object data){
      /*ONLY USE THIS METHOD FOR TESTING PURPOSES
      Makes a new PQNode using the input data, and then
      just adds it onto the end of the heap array like an ArrayList.
      Ex.
      MaxPQ pq = new MaxPQ()
      pq.add(11, "Kuma")
      pq.add(12, "Maxine")
      pq.add(13, "Jina")
      pq.add(14, "Alejandro")
      pq.toString() -> [null, 11-Kuma, 12-Maxine, 13-Jina, 14-Alejandro]
      */
      if(this.heap.length == (this.size + 1)){
         this.resize();
      }
      this.heap[this.size + 1] = new PriorityNode(priority, data);
      this.size++;
   }
   public void swimUp(int i){
      /*If the node at index i has a higher priority than its
      parent, move that node up the tree until it is in the
      right spot.
      Input:
         this: a Max-Heap Priority Queue
         int i: the index of the node you want try swimming up
      Results:
         Side Effects: the node at index i is now at its proper
                       position in the heap
      Ex.
      MaxPQ pq = new MaxPQ()
      pq.simpleAdd(13, "Jina")
      pq.simpleAdd(12, "Maxine")
      pq.simpleAdd(11, "Kuma")
      pq.simpleAdd(14, "Alejandro")
      pq.toString() -> [null, 13-Jina, 12-Maxine, 11-Kuma, 14-Alejandro]
      pq.swimUp(4)
      pq.toString() -> [null, 14-Alejandro, 13-Jina, 11-Kuma, 12-Maxine]
      */
      while (i > 1) { 
         //if node in question is greater than the parent switch
         if(this.heap[i].getPriority() > this.heap[i/2].getPriority()){
            PriorityNode temp = this.heap[i/2];
            this.heap[i/2] = this.heap[i];
            this.heap[i] = temp;
            i = i/2;
         }
         else {
            break;
         }
      }
   }  
   public void add(int priority, Object data){
   
      /* ACTUAL ADD, USE THIS ONCE IT WORKS
      Add a new node to the queue with the given priority and
      data.Put it at the end of the queue, and then swim it up
      as necessary.
      Input:
         this: the Max-Heap Priority Queue you are adding to
         int priority: the priority of this node
         Object data: the data associated with this node
      Result:
         Side Effects: a new node containing this data has been
                       added to the queue, and proper heap order
                       has been maintained
      Ex.
      MaxPQ pq = new MaxPQ()
      pq.add(11, "Kuma")
      pq.toString() -> [null, 11-Kuma]
      pq.add(12, "Maxine")
      pq.toString() -> [null, 12-Maxine, 11-Kuma]
      pq.add(13, "Jina")
      pq.toString() -> [null, 13-Jina, 11-Kuma, 12-Maxine]
      pq.add(14, "Alejandro")
      pq.toString() -> [null, 14-Alejandro, 13-Jina, 12-Maxine, 11-Kuma]
      */
      
      if(this.heap.length == (this.size + 1)) {
      
         this.resize();
         
      }
      this.heap[this.size + 1] = new PriorityNode(priority, data);
      this.size++;
      swimUp(this.size);
   }
   public void sinkDown(int i){
      /*If the node at index i has a lower priority than either
      of its children, swap that node with the highest priority 
      child, then repeat the process until the initial node is
      in the right spot.
      Input:
         this: a Max-Heap Priority Queue
         int i: the index of the node you want try sinking down
      Results:
         Side Effects: the node at index i is now at its proper
                       position in the heap
      Ex.
      MaxPQ pq = new MaxPQ()
      pq.simpleAdd(11, "Kuma")
      pq.simpleAdd(14, "Alejandro")
      pq.simpleAdd(13, "Jina")
      pq.simpleAdd(12, "Maxine")
      pq.toString() -> [null, 11-Kuma, 14-Alejandro, 13-Jina, 12-Maxine]
      pq.sinkDown(1)
      pq.toString() -> [null, 14-Alejandro, 12-Maxine, 13-Jina, 11-Kuma]
      */    
      if (i <= this.size/2) { //check if there are children
         int leftc = 2*i;
         int rightc = 2*i+1;
         int lp;
         int rp;
         PriorityNode highestp;
         PriorityNode temp; //temporarily holds the node value of the one being switcharooed
         boolean isleftpriority = true;

         while ( (i <= this.size/2) ) { //while the i in quesiton has children
            
            if (2*i+1 <= this.size) { //check if there is a right child
               lp = this.heap[leftc].getPriority();
               rp = this.heap[rightc].getPriority();
               if (lp >= rp) {
                  highestp = this.heap[leftc];
                  isleftpriority = true;
               }
               else {
                  highestp = this.heap[rightc];
                  isleftpriority = false;
               }
            }
            else {
               if (this.heap[leftc].getPriority() > this.heap[i].getPriority()) {
                  highestp = this.heap[leftc];
               }
               else break;
            }
            temp = this.heap[i];
            this.heap[i] = highestp; //make the parent whichever node is highest priority
            if (isleftpriority == true) {
               this.heap[leftc] = temp;
               i = leftc;
            }
            else {
               this.heap[rightc] = temp;
               i = rightc;
            }
            leftc = 2*i;
            rightc = 2*i+1;
         }
      }
        
   }
   public PriorityNode remove() {
      /*Remove the next node from the queue. Then update the
      internal storage to maintain the heap order.
      Input:
         this: a Max-Heap Priority Queue to remove from
      Results:
         Return: the node removed from the queue
         Side Effects: the heap has been updated to maintain
         the heap ordering
      Ex.
      MaxPQ pq = new MaxPQ()
      pq.add(11, "Kuma")
      pq.add(12, "Maxine")
      pq.add(13, "Jina")
      pq.add(14, "Alejandro")
      pq.toString() -> [null, 14-Alejandro, 13-Jina, 12-Maxine, 11-Kuma]
      
      pq.remove() -> 14-Alejandro
      pq.toString()-> [null, 13-Jina, 11-Kuma, 12-Maxine]
      
      pq.remove() -> 13-Jina
      pq.toString() -> [null, 12-Maxine, 11-Kuma]
      
      pq.remove() -> 12-Maxine
      pq.toString() -> [null, 11-Kuma]
      
      pq.remove() -> 11-Kuma
      pq.toString() -> [null]
      
      pq.remove() -> null
      pq.toString() -> [null]
      
      pq.remove() -> null
      pq.toString() -> [null]
      */
      if (this.size > 0) {
         PriorityNode temp = this.heap[1]; //store value of first parent node
         PriorityNode last = this.heap[this.size]; //this is the last node
         this.heap[1] = last;
         this.heap[this.size] = null;
         this.size--;
         sinkDown(1);
         return temp;
      }
      return null;
      
   } 
}
   
   
      