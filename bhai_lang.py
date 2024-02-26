import os

with open("mera.bhai", "r") as f:
    code = f.read()

# add some text to the top of the file
code = "package compiled;\n" + code

replace_list = {
    "aap jo kah rahe hain main use nahin sun raha hoon": 'Override',
    "ise chhodo": "break",
    "naya": "new",
    "haan ya nahin": "boolean",
    "bhai ye hai": "int",
    "jab tak": "while",
    "agar bhai": "if",
    "warna bhai": "else",
    "nahi to bhai": "else if",
    "laana": "import",
    "dekhne_wala": "java.util.Scanner",
    "khula": "public",
    "nahin": "void",
    "kuch": "class",
    "maaya": "abstract",
    "bhol bhai ": "System.out.println",
    "sahi": "true",
    "galat": "false",
    "vaapas karo": "return",
    "surakshit": 'protected',
    "dashamalav": 'double',
    "raaz": "private",
    "agalaInt": "nextInt",
    "agalaDouble": "nextDouble",
    "ye.": "this.",
    'rok': "locked",
    "dekh bhai": "Scanner",
    "meree baat suno bhai": "new Scanner(System.in)",
    "shaanti": "static",
}

for key, value in replace_list.items():
    code = code.replace(key, value)

# save it to a file called bhai.java
with open("compiled/Main.java", "w") as f:
    f.write(code)

# run the java file
os.system("cd compiled/ && javac Main.java")
os.system("java compiled.Main")