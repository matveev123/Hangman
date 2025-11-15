package image;

public class Human {

    public String draw(int count) {
        switch (count) {
            case 1: {
                return "    О\n";
            }
            case 2: {
                return "   \\\n    О\n" + "     /\n";
            }
            case 3: {
                return "   \\\n    О\n" + "    | /";
            }
            case 4: {
                return "   \\\n    О\n" + "  \\ | /";
            }
            case 5: {
                return "   \\\n    О\n" + "  \\ | /\n" + "    |";
            }
            case 6: {
                return "   \\\n    О\n" + "  \\ | /\n" + "    |\n" + "   /";
            }
            case 7: {
                return "   \\\n    О\n" + "  \\ | /\n" + "    |\n" + "   / \\";
            }
        }
        return "xxxx";
    }
}
