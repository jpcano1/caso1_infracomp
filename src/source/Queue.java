package source;

import java.util.Iterator;

/**
 * 
 * @author Juan Pablo Cano y Andrés González
 *
 */
public class Queue<T>
{
	@SuppressWarnings("hiding")
	public class Node<T>
	{
		private Node<T> next;

		private T element;

		public Node(T pElement)
		{
			next = null;
			element = pElement;
		}

		public Node<T> getNext()
		{
			return next;
		}

		public void setNext(Node<T> pNode)
		{
			next = pNode;
		}

		public T getElement()
		{
			return element;
		}

		public void setElement(T pElement)
		{
			element = pElement;
		}
	}

	//--------------------------------------
	//Attributes
	//--------------------------------------
	private Node<T> head;

	private Node<T> tail;

	private int size;

	//--------------------------------------
	//Constructors
	//--------------------------------------
	public Queue()
	{
		head = tail = null;
		size = 0;
	}

	//--------------------------------------
	//Methods
	//--------------------------------------
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}
		
	/**
	 * 
	 * @return
	 */
	public Node<T> getHead()
	{
		return head;
	}
	
	/**
	 * 
	 * @return
	 */
	public Node<T> getTail()
	{
		return tail;
	}
	
	/**
	 * Este metodo retorna el tamaño de la cola
	 * @return el tamaño de la cola almacenado en un integer.
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Este elemento encola elementos en la cola
	 * @param t el elemento que se encolará
	 */
	public void enqueue(T t)
	{
		Node<T> nuevo = new Node<T>(t);
		if(tail == null)
		{
			head = tail = nuevo;
			size++;
		}
		else
		{
			tail.setNext(nuevo);
			tail = nuevo;
			size++;
		}
	}

	/**
	 * Un iterador simple en la cola
	 * @return un iterador.
	 */
	public Iterator<T> iterator()
	{
		return new Iterator<T>()
		{
			Node<T> actual = head;

			@Override
			public boolean hasNext()
			{
				return actual != null;
			}

			@Override
			public T next() 
			{
				if(hasNext())
				{
					T data = actual.getElement();
					actual = actual.getNext();
					return data;
				}
				return null;
			}
		};
	}

	/**
	 * El primer elemento de la cola
	 * @return el tope de la cola
	 */
	public T dequeue()
	{
		Node<T> eliminado = head;
		head = head.getNext();
		if(head == null)
		{
			tail = null;
		}
		size--;
		return eliminado.getElement();
	}
}
