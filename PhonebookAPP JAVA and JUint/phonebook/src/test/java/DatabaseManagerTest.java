import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import com.phonebook.DatabaseManager;

public class DatabaseManagerTest {

    @BeforeAll
    public static void setUp() {
        DatabaseManager.createTable();

    }

    @AfterAll
    public static void tearDown() {
        DatabaseManager.deleteDatabase();
    }

    @Test
    public void testAddPhoneNumber_ValidData() {
        assertTrue(DatabaseManager.addPhoneNumber("01207464354", "Abdetrahman AAbodief", "abdelrahman@etample.com"));
    }

    @Test
    public void testAddPhoneNumber_InvalidPhoneNumber() {
        assertFalse(DatabaseManager.addPhoneNumber("1234567890", "Abdelrahman Abodief", "abdelrahman@example.com"));
    }

    @Test
    public void testAddPhoneNumber_InvalidEmail() {
        assertFalse(DatabaseManager.addPhoneNumber("01007464654", "Abdelrahman Abodief", "invalidemail"));
    }

    @Test
    public void testAddPhoneNumber_InvalidName() {
        assertFalse(DatabaseManager.addPhoneNumber("01007464654", "Abdel123", "abdelrahman@example.com"));
    }

    @Test
    public void testAddPhoneNumber_DuplicatePhoneNumber() {
        DatabaseManager.addPhoneNumber("01107464654", "Abdelrahman Abodief", "aabdelrahman@example.com");
        assertFalse(DatabaseManager.addPhoneNumber("01107464654", "Mohamed Ali", "mmohamed.ali@example.com"));
    }

    @Test
    public void testUpdatePhoneNumber_ValidData() {
        DatabaseManager.addPhoneNumber("01007464654", "Abdelrahman Abodief", "abdelrahman@example.com");
        assertTrue(DatabaseManager.updatePhoneNumber("01007464654", "01006464656", "Abdelrahman Updated",
                "abdelrahman.updated@example.com"));
    }

    @Test
    public void testUpdatePhoneNumber_InvalidData() {
        DatabaseManager.addPhoneNumber("01007464654", "Abdelrahman Abodief", "abdelrahman@example.com");
        assertFalse(DatabaseManager.updatePhoneNumber("01007464654", "1234567890", "Abdelrahman Updated",
                "abdelrahman.updated@example.com"));
    }

    @Test
    public void testDeletePhoneNumber_ValidData() {
        DatabaseManager.addPhoneNumber("01007464654", "Abdelrahman Abodief", "abdelrahman@example.com");
        assertTrue(DatabaseManager.deletePhoneNumber("01007464654"));
    }

    @Test
    public void testDeletePhoneNumber_InvalidData() {
        assertFalse(DatabaseManager.deletePhoneNumber("01007464654"));
    }

    @Test
    public void testSearchPhoneNumberByName_ValidData() {
        DatabaseManager.addPhoneNumber("01067464554", "Abdelrayman AAbodief", "abdelrahman@eyample.com");
        String result = DatabaseManager.searchPhoneNumberByName("Abdelrayman AAbodief");
        assertNotNull(result);
        assertTrue(result.contains("01067464554"));
    }

    @Test
    public void testSearchPhoneNumberByName_InvalidData() {
        String result = DatabaseManager.searchPhoneNumberByName("NonExistent Name");
        assertNull(result);
    }

    @Test
    public void testSearchPhoneNumberByEmail_ValidData() {
        DatabaseManager.addPhoneNumber("01047464654", "Abdelrrrahman Abodief", "abdelrahman@examyple.com");
        String result = DatabaseManager.searchPhoneNumberByEmail("abdelrahman@examyple.com");
        assertNotNull(result);
        assertTrue(result.contains("01047464654"));
    }

    @Test
    public void testSearchPhoneNumberByEmail_InvalidData() {
        String result = DatabaseManager.searchPhoneNumberByEmail("nonexistent@example.com");
        assertNull(result);
    }

    @Test
    public void testShowAllPhoneNumbers() {
        DatabaseManager.addPhoneNumber("01207464654", "AAbdelrahman Abodief", "abdelrahmann@example.com");
        DatabaseManager.addPhoneNumber("01206464656", "MMohamed Ali", "mohamed.aali@example.com");
        DatabaseManager.showAllPhoneNumbers();
    }

    @Test
    public void testAddPhoneNumber_MaxLengthName() {
        String longName = "A".repeat(255);
        assertTrue(DatabaseManager.addPhoneNumber("01507464654", longName, "valid.email@example.com"));
    }

    @Test
    public void testAddPhoneNumber_MaxLengthEmail() {
        String longEmail = "a".repeat(242) + "@example.com";
        assertTrue(DatabaseManager.addPhoneNumber("01557664654", "Abdelrrajman Abodief", longEmail));
        String longEmailE = "a".repeat(243) + "@example.com";
        assertFalse(DatabaseManager.addPhoneNumber("01555664654", "Abfelrrajman Abodief", longEmailE));

    }

    @Test
    public void testAddPhoneNumber_WithRandomDataRepeatedly() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        final String PHONE_PREFIX = "010";
        final String EMAIL_DOMAIN = "@example.com";

        Random random = new Random();

        for (int j = 0; j < 100; j++) {
            StringBuilder uniqueName = new StringBuilder();
            StringBuilder uniquePhoneNumber = new StringBuilder(PHONE_PREFIX);
            StringBuilder uniqueEmail = new StringBuilder();

            int nameLength = 5 + random.nextInt(6);
            for (int i = 0; i < nameLength; i++) {
                uniqueName.append(chars.charAt(random.nextInt(chars.length())));
            }

            for (int i = 0; i < 8; i++) {
                uniquePhoneNumber.append(random.nextInt(10));
            }

            int emailLength = 5 + random.nextInt(6);
            String chars2 = "abcdefghijklmnopqrstuvwxyz";
            for (int i = 0; i < emailLength; i++) {
                uniqueEmail.append(chars2.charAt(random.nextInt(chars2.length())));
            }
            uniqueEmail.append(EMAIL_DOMAIN);

            assertTrue(DatabaseManager.addPhoneNumber(uniquePhoneNumber.toString(), uniqueName.toString(),
                    uniqueEmail.toString()));
        }
    }

    @Test
    public void testAddPhoneNumber_InvalidCharactersInPhoneNumber() {
        assertFalse(DatabaseManager.addPhoneNumber("abcd1234efgh", "Abdelrahman Abodief", "abdelrahman@example.com"));
    }

    @Test
    public void testAddPhoneNumber_EmptyFields() {
        assertFalse(DatabaseManager.addPhoneNumber("", "", ""));
    }

    @Test
    public void testUpdatePhoneNumber_DuplicateNewPhoneNumber() {
        DatabaseManager.addPhoneNumber("01007464654", "Abdelrahman Abodief", "abdelrahman@example.com");
        DatabaseManager.addPhoneNumber("01106464655", "Mohamed Ali", "mohamed.ali@example.com");
        assertFalse(DatabaseManager.updatePhoneNumber("01007464654", "01106464655", "Updated Name",
                "updated.email@example.com"));
    }

    @Test
    public void testDeletePhoneNumber_NonExistent() {
        assertFalse(DatabaseManager.deletePhoneNumber("01507464654"));
    }

    @Test
    public void testAddPhoneNumber_EmailExceedsMaxLength() {
        String longEmail = "a".repeat(243) + "@example.com";
        assertFalse(DatabaseManager.addPhoneNumber("01557664654", "Abdelrahman Abodief", longEmail));
    }

    @Test
    public void testAddPhoneNumber_NameWithMultipleSpaces() {
        assertTrue(DatabaseManager.addPhoneNumber("01587464654", "Abdel Rahman Abodief", "abdelrahman@example.com"));
    }
}
