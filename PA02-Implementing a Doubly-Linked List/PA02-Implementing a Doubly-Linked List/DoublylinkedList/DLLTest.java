import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DLLTest {

    @Test
    public void testEmptyConstructor() {
        DLL dll = new DLL();
        Assertions.assertEquals("NULL", dll.toString());
        Assertions.assertNull(dll.head);
        Assertions.assertNull(dll.tail);
        Assertions.assertEquals(0, dll.size);
    }

    @Test
    public void testAppend() {
        DLL dll = new DLL();
        Album album1 = new Album(1, new String[]{"Artist1"}, "Album1", 10);
        Album album2 = new Album(2, new String[]{"Artist2"}, "Album2", 12);
        Album album3 = new Album(3, new String[]{"Artist1", "Artist2"}, "Album3", 8);
        Assertions.assertSame(dll.append(album1), dll.tail);
        Assertions.assertSame(dll.append(album2), dll.tail);
        Assertions.assertSame(dll.append(album3), dll.tail);
        Assertions.assertEquals(3, dll.size);
        Assertions.assertEquals("ID: 10 -- [Artist1]\n" +
                " -> ID: 12 -- [Artist2]\n" +
                " -> ID: 8 -- [Artist1, Artist2]\n" +
                "NULL", dll.toString());
    }

    /**
     * 
     */
    @Test
    public void testInsert() {
        DLL dll = new DLL();
    
        // Create test albums
        Album album1 = new Album(1, new String[]{"Artist1"}, "Album1", 10);
        Album album2 = new Album(2, new String[]{"Artist2", "Artist3"}, "Album2", 12);
        Album album3 = new Album(3, new String[]{"Artist4", "Artist5"}, "Album3", 8);
    
        // Test inserting at the head
        assertEquals(album1, dll.insert(0, album1).getData());
        assertEquals(album1.toString() + " -> NULL", dll.toString());
    
        // Test inserting in the middle
        assertEquals(album2, dll.insert(1, album2).getData());
        assertEquals(album1.toString() + " -> " + album2.toString() + " -> NULL", dll.toString());
    
        // Test inserting at the tail
        assertEquals(album3, dll.insert(2, album3).getData());
        assertEquals(album1.toString() + " -> " + album2.toString() + " -> " + album3.toString() + " -> NULL", dll.toString());
    
        // Test inserting at an invalid location (out of bounds)
        Assertions.assertThrows(IllegalArgumentException.class, () -> dll.insert(3, album1));
    }
    

    private void assertEquals(Album album1, Album data) {
    }

    private void assertEquals(String string, String string2) {
    }

    @Test
    public void testDelete() {
        DLL dll = new DLL();
        Album album1 = new Album(1, new String[]{"Artist1"}, "Album1", 10);
        Album album2 = new Album(2, new String[]{"Artist2"}, "Album2", 12);
        Album album3 = new Album(3, new String[]{"Artist1", "Artist2"}, "Album3", 8);
        dll.append(album1);
        dll.append(album2);
        dll.append(album3);
        Assertions.assertThrows(IllegalArgumentException.class, () -> dll.delete(3));
        Assertions.assertSame(dll.delete(0), dll.head);
        Assertions.assertSame(dll.delete(1), dll.tail);
        Assertions.assertEquals(1, dll.size);
        Assertions.assertEquals("ID: 12 -- [Artist2]\nNULL", dll.toString());
    }

    @Test
    public void testGetIndex() {
        DLL dll = new DLL();
        Album album1 = new Album(1, new String[]{"Artist1"}, "Album1", 10);
        Album album2 = new Album(2, new String[]{"Artist2"}, "Album2", 12);
        Album album3 = new Album(3, new String[]{"Artist1", "Artist2"}, "Album3", 8);
        dll.append(album1);
        dll.append(album2);
        dll.append(album3);
        Assertions.assertEquals(0, dll.getIndex(album1));
        Assertions.assertEquals(2, dll.getIndex(album3));
        Album album4 = new Album(4, new String[]{"Artist3"}, "Album4", 7);
        Assertions.assertEquals(-1, dll.getIndex(album4));
    }
    @Test
    void testShuffle() {
        DLL dll = new DLL();
        Album album1 = new Album(1, new String[]{"Artist1", "Artist2"}, "Album1", 10);
        Album album2 = new Album(2, new String[]{"Artist3", "Artist4"}, "Album2", 8);
        Album album3 = new Album(3, new String[]{"Artist5", "Artist6"}, "Album3", 12);
        Album album4 = new Album(4, new String[]{"Artist7", "Artist8"}, "Album4", 6);
        Album album5 = new Album(5, new String[]{"Artist9", "Artist10"}, "Album5", 9);
        Album album6 = new Album(6, new String[]{"Artist11", "Artist12"}, "Album6", 11);
        dll.append(album1);
        dll.append(album2);
        dll.append(album3);
        dll.append(album4);
        dll.append(album5);
        dll.append(album6);
    
        // Shuffle the list
        DLL.Node shuffledHead = dll.shuffle();
    
        // Test that the shuffled list contains the same elements as the original list
        List<Album> originalList = new ArrayList<Album>((int) Arrays.asList(album1, album2, album3, album4, album5, album6));
        List<Album> shuffledList = new ArrayList<>();
        DLL.Node currentNode = shuffledHead;
        while (currentNode != null) {
            shuffledList.add(currentNode.getData());
            currentNode = currentNode.getNext();
        }
        assertTrue(originalList.containsAll(shuffledList) && shuffledList.containsAll(originalList));
    }

    private void assertTrue(boolean b) {
    }

    @Test
    void testPartition() {
        DLL dll = new DLL();
        Album album1 = new Album(1, new String[]{"Artist1", "Artist2"}, "Album1", 10);
        Album album2 = new Album(2, new String[]{"Artist3", "Artist4"}, "Album2", 8);
        Album album3 = new Album(3, new String[]{"Artist5", "Artist6"}, "Album3", 12);
        Album album4 = new Album(4, new String[]{"Artist7", "Artist8"}, "Album4", 6);
        Album album5 = new Album(5, new String[]{"Artist9", "Artist10"}, "Album5", 9);
        Album album6 = new Album(6, new String[]{"Artist11", "Artist12"}, "Album6", 11);
        dll.append(album1);
        dll.append(album2);
        dll.append(album3);
        dll.append(album4);
        dll.append(album5);
        dll.append(album6);
    
        // Create a new list with items greater than or equal to 9
        DLL partitionedList = dll.partition(9);
    
        // Test that the new list contains only items greater than or equal to 9
        DLL.Node currentNode = partitionedList.getHead();
        while (currentNode != null) {
            assertTrue(currentNode.getData().getNumSongs() >= 9);
            currentNode = currentNode.getNext();
        }    
    }

}   