public class CircularArrayQueue<T> implements QueueADT<T> {
        private int front;
        private int rear;
        private int count;
        private T[] queue;
        private final int DEFAULT_CAPACITY = 20;

        //initalize front to 1, rear to D.C., count to 0
        public CircularArrayQueue() {
                front = 1;
                rear = DEFAULT_CAPACITY;
                count = 0;
                queue = (T[]) (new Object[DEFAULT_CAPACITY]);
        }
        //same as first contructor but adding in paramater.
        public CircularArrayQueue(int initialCapacity) {
                front = 1;
                rear = initialCapacity;
                count = 0;
                queue = (T[]) (new Object[initialCapacity]);
        }
        	//takes in an element of generic type and adds to rear of queue
        public void enqueue(T element) {
        	 if (size() == queue.length)
        	//expand if full
                 expandCapacity();
         if (rear == queue.length-1) {
                 queue[rear] = element;
                 rear = queue.length;
         }
         else {
                 rear = (rear + 1) % queue.length;
                 queue[rear - 1] = element;
         }
         count++;
 }
       

        public T dequeue() throws EmptyCollectionException
        {
           if (isEmpty())
              throw new EmptyCollectionException ("queue");

           T result = queue[0];

           rear--;

           // shift the elements 
           for (int scan=0; scan < rear; scan++)
              queue[scan] = queue[scan+1];

      
           queue[rear] = null;
      
           return result;
        }
        
        public T first() throws EmptyCollectionException
        {
            if (isEmpty()) {
            	throw new EmptyCollectionException("attempting to access empty LinearArrayQueue");
            }
            if (front-1>=0) 
            	return queue[front - 1];
            else {
            	return queue[queue.length - 1];
            }
         }
        	//returns true if the queue is empty, and false otherwise
        public boolean isEmpty() {
        	if(count==0)    
            {
                return true;
            }
            else
                return false;
        }
        	//returns the number of items on the queue
        public int size() {
                return count;
        }
        	//returns the front index value
        public int getFront() {
                return front;
        }
        	//returns the real index value
        public int getRear() {
                return rear;
        }
        	//returns the current length of the array
        public int getLength() {
                return queue.length;
        }
        	//returns the string containing 'queue'
        public String toString() {
        	{
        		String result = "QUEUE: ";
        		int scan = 0;
        		if(isEmpty())
        		{
        		return "The queue is empty";
        		}
        		  
        		for(int i=0;i<count-1;i++)
        		{
        		result+=queue[i]+", ";
        		}
        		result+=queue[count-1]+".";
        		return result;
        		
        	}
        }
        	//
        private void expandCapacity() {
      	  {
      	    T[] larger = (T[])(new Object[queue.length + 20]);
      	    
      	    for(int scan=0; scan < count; scan++)
      	    {
      	      larger[scan] = queue[front];
      	      front=(front+1) % queue.length;
      	    }
      	    
      	    front = 0;
      	    rear = count;
      	    queue = larger;
      	  }
        }
}