package org.xcolab.logwatcher;

/**
 * Created by patrickhiesel on 25/10/14.
 */
public class JaroWinklerSimilarity {


    final static double DEFAULT_SCALING_FACTOR = 0.1;

    /**
     * Calculates the similarity score of objects, where 0.0 implies absolutely no similarity
     * and 1.0 implies absolute similarity.
     *
     * @param first The first string to compare.
     * @param second The second string to compare.
     * @return A number between 0.0 and 1.0.
     */
    public static double score(String first, String second)
    {
        String shorter;
        String longer;

        // Determine which String is longer.
        if (first.length() > second.length())
        {
            longer = first.toLowerCase();
            shorter = second.toLowerCase();
        }
        else
        {
            longer = second.toLowerCase();
            shorter = first.toLowerCase();
        }

        // Calculate the half length() distance of the shorter String.
        int halflength = (shorter.length() / 2) + 1;

        // Find the set of matching characters between the shorter and longer strings. Note that
        // the set of matching characters may be different depending on the order of the strings.
        String m1 = getSetOfMatchingCharacterWithin(shorter, longer, halflength);
        String m2 = getSetOfMatchingCharacterWithin(longer, shorter, halflength);


        // If one or both of the sets of common characters is empty, then
        // there is no similarity between the two strings.
        if (m1.length() == 0 || m2.length() == 0) return 0.0;

        // If the set of common characters is not the same size, then
        // there is no similarity between the two strings, either.
        if (m1.length() != m2.length()) return 0.0;

        // Calculate the number of transpositions between the two sets
        // of common characters.
        int transpositions = transpositions(m1, m2);

        // Calculate the distance.
        double jaro =
                (m1.length() / ((double)shorter.length()) +
                        m2.length() / ((double)longer.length()) +
                        (m1.length() - transpositions) / ((double)m1.length())) / 3.0;

        int cl = commonPrefixLength(first, second);

        // The Jaro–Winkler distance uses a prefix scale which gives more favorable ratings
        // to strings that match from the beginning for a set prefix length.
        double winkler = jaro + (DEFAULT_SCALING_FACTOR * cl * (1.0 - jaro));

        return winkler;

    }

    /**
     * Calculates the number of characters from the beginning of the strings that match exactly one-to-one,
     * up to a maximum of four (4) characters.
     * @param first The first string.
     * @param second The second string.
     * @return A number between 0 and 4.
     */
    private static int commonPrefixLength(String first, String second)
    {
        String shorter;
        String longer;

        // Determine which string is longer.
        if (first.length() > second.length())
        {
            longer = first.toLowerCase();
            shorter = second.toLowerCase();
        }
        else
        {
            longer = second.toLowerCase();
            shorter = first.toLowerCase();
        }

        int result = 0;

        // Iterate through the shorter string.
        for (int i = 0; i < shorter.length(); i++)
        {
            if (shorter.charAt(i) != longer.charAt(i))
            {
                break;
            }
            result++;
        }

        // Limit the result to 4.
        return result > 4? 4: result;
    }


    private static String getSetOfMatchingCharacterWithin(String first, String second, int limit)
    {

        StringBuilder common = new StringBuilder();
        StringBuilder copy = new StringBuilder(second);
        for (int i = 0; i < first.length(); i++)
        {
            char ch = first.charAt(i);
            boolean found = false;

            // See if the character is within the limit positions away from the original position of that character.
            for (int j = Math.max(0, i - limit); !found && j < Math.min(i + limit, second.length()); j++)
            {
                if (copy.charAt(j) == ch)
                {
                    found = true;
                    common.append(ch);
                    copy.setCharAt(j,'*');
                }
            }
        }
        return common.toString();
    }

    /**
     * Calculates the number of transpositions between two strings.
     * @param first The first string.
     * @param second The second string.
     * @return The number of transpositions between the two strings.
     */
    private static int transpositions(String first, String second)
    {
        int transpositions = 0;
        for (int i = 0; i < first.length(); i++)
        {
            if (first.charAt(i) != second.charAt(i))
            {
                transpositions++;
            }
        }
        transpositions /= 2;
        return transpositions;
    }
}
