package com.hxy.OnlineDiningRoom.util;

import com.hxy.OnlineDiningRoom.util.Page;
import org.junit.Test;
import static org.junit.Assert.*;

public class PageTest {

    @Test
    public void testIsHasPreviouse() {
        Page page = new Page(0, 8);
        page.setTotal(50);
        assertFalse(page.isHasPreviouse());

        page.setStart(1);
        assertTrue(page.isHasPreviouse());
    }

    @Test
    public void testIsHasNext() {
        Page page = new Page(0, 8);
        page.setTotal(50);
        assertTrue(page.isHasNext());

        page.setStart(48);
        assertFalse(page.isHasNext());
    }

    @Test
    public void testGetTotalPage() {
        Page page = new Page(0, 8);
        page.setTotal(50);
        assertEquals(7, page.getTotalPage());

        page.setTotal(57);
        assertEquals(8, page.getTotalPage());

        page.setTotal(0);
        assertEquals(1, page.getTotalPage());
    }

    @Test
    public void testGetLast() {
        Page page = new Page(0, 8);
        page.setTotal(50);
        assertEquals(48, page.getLast());

        page.setTotal(58);
        assertEquals(56, page.getLast());

        page.setTotal(0);
        assertEquals(0, page.getLast());
    }
}
