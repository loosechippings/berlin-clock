import org.junit.Test;
import static org.junit.Assert.*;

public class BerlinClockConverterTest {

	TimeConvert t=new BerlinClockConverter();

	@Test
	public void validTimeConvertedOk() {
		String f=t.convert("12:55:00");
		assertNotNull(f);
	}

	@Test(expected=IllegalArgumentException.class)
	public void invalidTimeThrowsAnException() {
		String f=t.convert("12:65:00");
		fail("should have thrown an exception");
	}

	@Test
	public void firstRowLampIsYellowWhenSecondsAreEven() {
		String f=t.convert("10:40:10");
		String topRowLight=f.split("\n")[0];
		assertEquals("first row light should be yellow","Y",topRowLight);
	}

	@Test
	public void firstRowLampIsOffWhenSecondsAreOdd() {
		String f=t.convert("10:40:11");
		String topRowLight=f.split("\n")[0];
		assertEquals("first row light should be off","O",topRowLight);
	}

	@Test
	public void secondRowHasTwoRedLampsWhenTenOclock() {
		String f=t.convert("10:40:11");
		String secondRowLights=f.split("\n")[1];
		assertEquals("second row should have 2 red, 2 off","RROO",secondRowLights);
	}

	@Test
	public void secondRowHasNoRedLampsWhenTwoOclock() {
		String f=t.convert("02:40:11");
		String secondRowLights=f.split("\n")[1];
		assertEquals("second row should have 0 red, 4 off","OOOO",secondRowLights);
	}

	@Test
	public void secondRowHasFourRedLampsWhenTwentyHours() {
		String f=t.convert("20:40:11");
		String secondRowLights=f.split("\n")[1];
		assertEquals("second row should have 4 red, 0 off","RRRR",secondRowLights);
	}

	@Test
	public void thirdRowHasThreeRedLampsWhenTwentyThreeHours() {
		String f=t.convert("23:40:11");
		String secondRowLights=f.split("\n")[2];
		assertEquals("second row should have 3 red, 1 off","RRRO",secondRowLights);		
	}

	@Test
	public void thirdRowHasNoRedLampsWhenTwentyHours() {
		String f=t.convert("20:40:11");
		String secondRowLights=f.split("\n")[2];
		assertEquals("second row should have 0 red, 4 off","OOOO",secondRowLights);		
	}

	@Test
	public void fourthRowHasTwoYellowAndOneRedWhenQuaterPastTheHour() {
		String f=t.convert("20:15:11");
		String thirdRowLights=f.split("\n")[3];
		assertEquals("should be 2 yellow 1 red","YYROOOOOOOO",thirdRowLights);
	}

	@Test
	public void fourthRowHasThreeYellowAndOneRedWhenTwentyPastTheHour() {
		String f=t.convert("20:20:11");
		String thirdRowLights=f.split("\n")[3];
		assertEquals("should be 3 yellow 1 red","YYRYOOOOOOO",thirdRowLights);
	}

	@Test
	public void fifthRowHasOneYellowWhenSixteenMinutesPast() {
		String f=t.convert("20:16:11");
		String fifthRowLights=f.split("\n")[4];
		assertEquals("should be 1 yellow, 3 off","YOOO",fifthRowLights);
	}

}