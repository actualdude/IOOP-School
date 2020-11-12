package kolekce;

import java.io.Serializable;
import java.util.Iterator;
import java.util.function.Function;

/**
 *
 * @author st52501
 */
public class Seznam<E> implements ISeznam<E>
{
	private Item<E> first, last, pointer;
	private int size, count;

	private class Item<E>
	{
		private E data;
		private Item<E> next;

		private Item(E data)
		{
			this.data = data;
		}

		public E getData()
		{
			return data;
		}

		public Item<E> getNext()
		{
			return next;
		}

		private void setNext(Item<E> next)
		{
			this.next = next;
		}
	}

	public Seznam(int maxSize) throws KolekceException
	{
		if (maxSize <= 0) {
			throw new KolekceException("Null or negative size of collection is not allowed");
		}

		this.size = maxSize;
	}

	@Override
	public int getPocet() // Gives me number of items in collection
	{
		return count;
	}

	@Override
	public int getVelikost() // Gives me max size of collection
	{
		return size;
	}

	@Override
	public boolean jePrazdny() // Ask if collection is empty
	{
		return this.getPocet() == 0;
	}

	@Override
	public void pridej(E data) throws KolekceException // Add new item to collection
	{
		if (count >= size) {
			throw new KolekceException("Collection reached maximum limit");
		}

		Item<E> item = new Item<>(data);

		if (getFirst() == null) {
			setFirst(item);
			setLast(item);
		} else {
			getLast().setNext(item);
			setLast(item);
		}

		count++;
	}

	@Override
	public void pridej(E... data) throws KolekceException
	{
		if ((count + data.length) <= size) {
			for (E item : data) {
				pridej(item);
			}
		}
		else {
			throw new KolekceException("Collection cannot add so many items");
		}
	}

	@Override
	public E zpristupni() throws KolekceException // Return current item of pointer
	{
		if (jePrazdny()) throw new KolekceException("Collection is empty");
		else if (pointer == null) throw new KolekceException("Collection pointer is not set");

		return pointer.getData();
	}

	@Override
	public void nastavPrvni() throws KolekceException // Pointer is set to first item of collection
	{
		if (jePrazdny()) throw new KolekceException("Collection is empty");
		this.pointer = getFirst();
	}

	@Override
	public void prejdiNaDalsi() throws KolekceException // Pointer is moved to next item
	{
		if (jePrazdny()) throw new KolekceException("Collection is empty");
		else if (pointer == null) throw new KolekceException("Collection pointer is not set");

		pointer = pointer.getNext();
	}

	@Override
	public void vlozZa(E data) throws KolekceException // Add item behind pointer
	{
		if (jePrazdny()) throw new KolekceException("Collection is empty");
		else if (pointer == null) throw new KolekceException("Collection pointer is not set");

		Item<E> newItem = new Item<>(data);
		newItem.setNext(pointer.getNext());
		pointer.setNext(newItem);
		count++;
	}

	@Override
	public E najdi(E klic) // Find item from collection by klic
	{
		Item<E> actual = getFirst();

		for (int i = 0; i < count; i++) {
			if (actual.getData().equals(klic)) {
				return actual.getData();
			}
			actual = actual.getNext();
		}

		return null;
	}

	@Override
	public E odeber(E klic) // Remove item from collection by klic
	{
		Item<E> actual = getFirst();
		Item<E> previous = null;

		for (int i = 0; i < count; i++) {
			if (actual.getData().equals(klic)) {
				if (actual == this.getFirst()) {
					setFirst(actual.getNext());
				}
				else if (previous != null) {
					previous.setNext(actual.getNext());;
				}
				count--;
				return actual.getData();
			}
			previous = actual;
			actual = actual.getNext();
		}

		return null;
	}

	@Override
	public void zrus() // Will clear the collection
	{
		setFirst(null);
		setLast(null);
		count = 0;
	}

	@Override
	public E[] toArray() // Takes all items and return them as array
	{
		Object[] outputArray = new Object[count];
		Item<E> actual = getFirst();

		for (int i = 0; i < count; i++) {
			outputArray[i] = actual.getData();
			actual = actual.getNext();
		}

		return (E[]) outputArray;
	}

	@Override
	public E[] toArray(E[] array) throws IllegalArgumentException
	{
		return null;
	}

	@Override
	public E[] toArray(Function<Integer, E[]> createFunction)
	{
		return null;
	}

	@Override
	public Iterator<E> iterator()
	{
		return new Iterator<E>()
		{
			Item<E> actual = Seznam.this.getFirst();
			Item<E> previous = null;

			@Override
			public boolean hasNext()
			{
				return this.actual != null;
			}

			@Override
			public E next()
			{
				this.previous = this.actual;
				this.actual = this.actual.getNext();

				return this.previous.getData();
			}
		};
	}

	public Item<E> getFirst()
	{
		return first;
	}

	public void setFirst(Item<E> first)
	{
		this.first = first;
	}

	public Item<E> getLast()
	{
		return last;
	}

	public void setLast(Item<E> last)
	{
		this.last = last;
	}
}
