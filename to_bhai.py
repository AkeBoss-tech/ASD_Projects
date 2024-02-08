import os

with open("Main.java", "r") as f:
    code = f.read()

replace_list_reverse = {
    'System.out.println': "bhol bhai ",
    "point": 'jagah',
    "Point": 'Jagah',
    "print": 'bolo',
    'Override': "aap jo kah rahe hain main use nahin sun raha hoon",
    'break': "ise chhodo",
    'new': "naya",
    'boolean': "haan ya nahin",
    'int': "bhai ye hai",
    'while': "jab tak",
    'if': "agar bhai",
    'else': "warna bhai",
    'else if': "nahi to bhai",
    'import': "laana",
    'java.util.Scanner': "dekhne_wala",
    'public': "khula",
    'void': "nahin",
    'class': "kuch",
    'abstract': "maaya",
    'true': "sahi",
    'false': "galat",
    'return': "vaapas karo",
    'protected': 'surakshit',
    'double': 'dashamalav',
    'private': "raaz",
    'nextInt': "agalaInt",
    'nextDouble': "agalaDouble",
    'this.': "ye.",
    'locked': 'rok',
    'Scanner': "dekh bhai",
    'new Scanner(System.in)': "meree baat suno bhai",
    'static': "shaanti",
}

for key, value in replace_list_reverse.items():
    code = code.replace(key, value)

# Save it to a file called mera.bhai
with open("mera.bhai", "w") as f:
    f.write(code)

# Optionally, you can run the modified Python code to execute the Java file
# os.system("cd compiled/ && javac mera.bhai")
# os.system("java compiled.mera")
