#'/Users/kishan/Workspace/intelliJ-workspace/BloomFilterProject/src/BloomFilter.java',

import subprocess
import os

process = subprocess.Popen('java -cp .Workspace.TestProject.bin.VirtualMemorySimulatorPart3')
pid=str(process.pid)
os.popen('java .Workspace.TestProject.bin.JVMRuntimeClient -pid' + pid)
print(pid)
exit()