# protobuf

## 命令

- protoc.exe --java_out=../../java *.proto
- --java_out指向java文件生成路径，不需要指定到具体的包，具体的包在proto文件中的option中指定
- -I是指定引入文件的路径