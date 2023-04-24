package ca.mcgill.ecse.flexibook.persistence;

import ca.mcgill.ecse.flexibook.model.FlexiBook;

public class FlexiBookPersistence {

    private static String filename = "data.flexibook";
    
    public static void setFilename(String filename) {
		FlexiBookPersistence.filename = filename;
	}

    public static void save(FlexiBook fb) {
        PersistenceObjectStream.serialize(fb);
    }

    public static FlexiBook load() {
        PersistenceObjectStream.setFilename(filename);
        FlexiBook fb = (FlexiBook) PersistenceObjectStream.deserialize();
        // model cannot be loaded - create empty FlexiBook
        if (fb == null) {
            fb = new FlexiBook();
            System.out.println("FlexiBook is null");
        }
        else {
        	fb.reinitialize();
        }
        return fb;
    }

}