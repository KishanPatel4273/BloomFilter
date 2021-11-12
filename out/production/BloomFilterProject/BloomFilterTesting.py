import os.path, subprocess
from subprocess
import STDOUT, PIPE

def compile_java(java_file):
   subprocess.check_call(['javac', java_file])

def execute_java(java_file, stdin):
   java_class, ext = os.path.splitext(java_file)
// cmd = ['java', java_class] change to
cmd = ['java',
   '-classpath', '/Users/kishan/Workspace/intelliJ-workspace/BloomFilterProject/src/BloomFilter.java',
   'BloomFilter'
]
proc = subprocess.Popen(cmd, stdin = PIPE, stdout = PIPE, stderr = STDOUT)
stdout, stderr = proc.communicate(stdin)

compile_java('BloomFilter.java')
execute_java('BloomFilter.java', 'data.json', 'output_010.csv')