import java.util.Arrays;
import java.util.Objects;

public class ExecParser {
    static String[] mem = new String[0];

    private static int apMem() {
        String[] temp = mem;
        mem = new String[mem.length+1];

        for (int i = 0; i < temp.length; i++) {
            mem[i]=temp[i];
        }
        mem[mem.length-1] = "";
        return mem.length-1;
    }

    public static void kill() {
        System.exit(0);
    }

    public static void main(String[] args) {
        int exec_sector;
        if (args.length == 0) return;

        String file = FileSystem.getFile(args[0]);
        if (Objects.equals(file, "")) return;
        String[] file_s = file.split("\\^");

        exec_sector = apMem();

        int i = 0;
        boolean running = true;

        while (running) {
            if (file_s[i].charAt(0) == '@') {
                String s = "";
                String s2 = "";

                for (int j = 1; file_s[i].charAt(j) != '>'; j++) {
                    s+=file_s[i].charAt(j);
                }

                for (int j = s.length()+2; j < file_s[i].length(); j++) {
                    s2+=file_s[i].charAt(j);
                }

                if (Objects.equals(s2, "KILLSIG")) {
                    switch (s) {
                        case "shell":
                            Shell.kill();
                            break;
                        case "boot":
                            Boot.kill();
                            break;
                        case "exec":
                            running=false;
                            break;
                    }
                }
            } else if (file_s[i].charAt(0) == '$' && file_s[i].charAt(1) == '<') {
                String s = "";

                for (int j = 2; j < file_s[i].length(); j++) {
                    s+=file_s[i].charAt(j);
                }

                mem[exec_sector] += s;
            } else if (file_s[i].charAt(0) == '<') {
                String s = "";
                String s2 = "";

                for (int j = 1; file_s[i].charAt(j) != '-'; j++) {
                    s+=file_s[i].charAt(j);
                }

                for (int j = s.length()+2; j < file_s[i].length(); j++) {
                    s2+=file_s[i].charAt(j);
                }

                for (int j = Integer.parseInt(s); j < Integer.parseInt(s2); j++) {
                    System.out.print(mem[exec_sector].charAt(j));
                }
            }
            i++;
            if (file_s.length == i) i=0;
        }
    }
}
