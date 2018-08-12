package com.example.demo;

import com.example.demo.calculator.Calculator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

// @RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
    @Mock
    public Calculator calculator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {

        // create mock
        List mockitoList = mock(List.class);

        // using mock object
        mockitoList.add("aaa");
        mockitoList.clear();

        // verification
        verify(mockitoList).add("aaa");
        verify(mockitoList).clear();
    }

    @Test
    public void test2() {
        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());
        System.out.println(mockedList.get(0));
        // following code will throw exception
        // System.out.println(mockedList.get(1));
        System.out.println(mockedList.get(999));

        // Argument matcher
        verify(mockedList).get(0);
        when(mockedList.get(anyInt())).thenReturn("element");
        System.out.println(mockedList.get(999));
        mockedList.contains("more than 5");
        verify(mockedList).contains(argThat((someString) -> ((String) someString).length() > 5));

        verify(mockedList, atLeastOnce()).get(0);

        // for void methods
        doThrow(new RuntimeException()).when(mockedList).clear();
        // mockedList.clear();

        // In order
        List<String> singleMock = mock(List.class);

        singleMock.add("first");
        singleMock.add("second");
        // Object... mocks
        InOrder inOrder = inOrder(singleMock);
        inOrder.verify(singleMock).add("first");
        inOrder.verify(singleMock).add("second");

        List mockOne = mock(List.class);
        List mockTwo = mock(List.class);
        mockOne.get(1);
        mockOne.get(2);
        verify(mockOne).get(1);
        verify(mockOne).get(2);
        verifyZeroInteractions(mockTwo);
        verifyNoMoreInteractions(mockOne);
    }

    @Test
    public void test3() {
        when(calculator.add(2, 3)).thenReturn(6);
        System.out.println(calculator.add(2, 3));
        when(calculator.add(anyInt(), anyInt())).thenAnswer((invocation) -> {
            Object[] args = invocation.getArguments();
            return (Integer) args[0] + (Integer) args[1];
        });
        System.out.println(calculator.add(5, 6));
    }

    @Test
    public void test4() {
        // spy
        List<String> list = new LinkedList<String>();
        List<String> spy = spy(list);

        spy.add("one");
        spy.add("two");
        System.out.println(spy);
        verify(spy).add("one");
        verify(spy).add("two");

        @SuppressWarnings("unchecked")
        List<String> list1 = mock(List.class, Mockito.RETURNS_SMART_NULLS);
        System.out.println(list1.get(1));
        // TODO: https://static.javadoc.io/org.mockito/mockito-core/2.20.0/org/mockito/Mockito.html#finding_redundant_invocations   #15

    }

    @Test
    /**
     * BDD Testing
     */
    public void shouldBuyBread() {
        // Seller seller = mock(Seller.class);
        // Shop shop = new Shop(seller);
        // Given
        // given(seller.askForBread()).willReturn(new Bread());
        // When
        // Goods goods = shop.buyBread();
        // then
        // assertTrue(goods.container(bread));
    }


}
