package reiff.fallingsand;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class SandTest {
    @Test
    public void string() {
        //given
        Sand sand = new Sand(3, 3);

        //when
        String actual = sand.toString();

        //then
        assertEquals("000\n000\n000\n", actual);
    }

    @Test
    public void put() {
        //given
        Sand sand = new Sand(3, 3);

        //when
        sand.put(1, 0);

        //then
        assertEquals("010\n000\n000\n", sand.toString());
    }

    @Test
    public void fall() {
        //given
        Sand sand = new Sand(3, 3);
        sand.put(1, 0);
        //when
        sand.fall();
        //then
        assertEquals("000\n010\n000\n", sand.toString());
    }

    @Test
    public void fallOnGround() {
        //given
        Sand sand = new Sand(3, 3);
        sand.put(1, 2);
        //when
        sand.fall();
        //then
        assertEquals("000\n000\n010\n", sand.toString());
    }


    @Test
    public void fallOnOtherSand() {
        //given
        Sand sand = new Sand(3, 3);
        sand.put(1, 0);
        sand.put(1, 2);
        //when
        sand.fall();
        //then
        assertEquals("000\n010\n010\n", sand.toString());
    }


    @Test
    public void fallToTheRight() {
        //given
        Sand sand = new Sand(3, 3);

        sand.put(1, 1);
        sand.put(1, 2);
        sand.put(0, 2); //left

        //when
        sand.fall();
        //then
        assertEquals("000\n000\n111\n", sand.toString());
    }

    @Test
    public void fallToTheRightOutOfBound() {
        //given
        Sand sand = new Sand(3, 3);

        sand.put(0, 1);
        sand.put(0, 2); //left
        sand.put(1, 2);
        
        //when
        sand.fall();
        //then
        assertEquals("000\n100\n110\n", sand.toString());
    }

    @Test
    public void fallToTheLeft() {
        //given
        Sand sand = new Sand(3, 3);
        sand.put(1, 1);
        sand.put(1, 2);
        sand.put(2, 2); //right
        //when
        sand.fall();
        //then
        assertEquals("000\n000\n111\n", sand.toString());
    }

    @Test
    public void fallToTheLeftOutOfBound() {
        //given
        Sand sand = new Sand(3, 3);
        sand.put(2, 1);
        sand.put(1, 2);
        sand.put(2, 2); //right
        //when
        sand.fall();
        //then
        assertEquals("000\n001\n011\n", sand.toString());
    }

    @Test
    public void fallSimultaneously() {
        //given
        Sand sand = new Sand(3, 3);
        sand.put(1, 0);
        sand.put(1, 1);
        //when
        sand.fall();
        //then
        assertEquals("000\n010\n010\n", sand.toString());
    }

    @Test
    public void fallRandomDirectionToTheRight() {
        //given
        Random random = mock();
        doReturn(true).when(random).nextBoolean();
        Sand sand = new Sand(3, 3, random);
        sand.put(1, 1);
        sand.put(1, 2);
        //when
        sand.fall();
        //then
        assertEquals("000\n000\n011\n", sand.toString());
    }

    @Test
    public void fallRandomDirectionToTheLeft() {
        //given
        Random random = mock();
        doReturn(false).when(random).nextBoolean();
        Sand sand = new Sand(3, 3, random);
        sand.put(1, 1);
        sand.put(1, 2);
        //when
        sand.fall();
        //then
        assertEquals("000\n000\n110\n", sand.toString());
    }

}