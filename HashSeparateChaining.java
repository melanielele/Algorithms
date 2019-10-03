/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS.Melanie Zhao
*/

public class HashSeparateChaining {

	private class Node {
		Entry entry;
		Node next;

		Node(Entry entry) { this.entry = entry; }
	}

	Node[] hashTable;
	int arraySize;
	int tableSize;

	public HashSeparateChaining(){
		hashTable = new Node[10];
		arraySize = 3;
	}

	/** TODO: Write a resizing method for the hash table.*/
	private void resize() {
		Node[] oldTable = hashTable;
	    hashTable = new Node[arraySize];
	    tableSize = 0;
	    for (int i = 0; i < oldTable.length;i++) {
	    	    Node currentNode = oldTable[i];
	    	    while (currentNode!=null) {
	    	    	 	put(currentNode.entry.getKey(),currentNode.entry.getValue());
	    	    	 	currentNode = currentNode.next;
	    
	    	    	 	}
	    }
	}

	/** Computes the index in the hash table from a given key */
	private int hash(String key) {
		int hashCode = key.hashCode();
		return (hashCode & 0x7fffffff) % arraySize;
	}

	/** Returns the number of entries in the hash table. */
	public int size() { return tableSize; }

	/** Checks whether the hash table is empty */
	public boolean isEmpty() { return tableSize == 0; }

	/** Returns the node containing the given key value if it exists in the table.
	    Otherwise, it returns a null value. */
	private Node findEntry(String key) {
		int index = hash(key);

		Node currentNode = hashTable[index];
		while (currentNode != null && !currentNode.entry.getKey().equals(key))
			currentNode = currentNode.next;

		return currentNode;

	}

	/** Returns the integer value paired with the given key, if the key is in the table.
		Otherwise, it returns null. */
	public Integer get(String key) {
		Node searchResult = findEntry(key);

		if (searchResult != null)
			return searchResult.entry.getValue();
		else
			return null;

	}

	/** If the given key is not in the table, creates a new entry and adds it to the table.
		Otherwise, it updates the value associated with the given key. */
	public void put(String key, Integer value) {
		Node searchResult = findEntry(key);

		// The key exists in the table
		if (searchResult != null){ 
			searchResult.entry.setValue(value);
			return;
		}

		// The key does not exist in the table
		Entry newEntry = new Entry(key, value);
		Node newNode = new Node(newEntry);

		if (tableSize/arraySize >= 5) {
			arraySize = (int) (1.5 * hashTable.length);
			resize();
		}
		
		
		int index = hash(key);
		if (hashTable[index] != null)
			newNode.next = hashTable[index];
		
		    hashTable[index] = newNode;
		    
            tableSize++;
	}

	/** Removes the entry containing the given key from the table, if the key exists in the table. */
	public void delete(String key) {
		Node searchResult = findEntry(key);
		// The key does not exist in the table.
		if (searchResult == null)
			return;

		// The key does exist in the table.
		
		if (tableSize/arraySize <= 3) {
			arraySize = (int) (hashTable.length/1.5);
			resize();
		}
		
		int index = hash(key);
		if (hashTable[index] == searchResult) {
			hashTable[index] = searchResult.next;
		    tableSize--;
		}
		else{
			Node currentNode = hashTable[index];
			while (currentNode.next != searchResult) {
				currentNode = currentNode.next;
				tableSize--;
			}
			currentNode.next = searchResult.next;
		}
	}

	/** Produces a string representation of the table. */
	@Override
	public String toString(){
		String output = "";

		for (int i = 0; i < arraySize; i++){
			output += "(" + i + "): ";
			Node currentNode = hashTable[i];
			if (currentNode == null)
				output += currentNode + "\n";
			else{
				while (currentNode != null){
					output += " -> " + currentNode.entry;
					currentNode = currentNode.next;
				}
				output += "\n";
			}
		}

		return output;

	}
}
