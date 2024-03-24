#!/bin/bash -i

# Command to start the Java process
java_command='java -Xmx4G -XX:+EnableDynamicAgentLoading --class-path target/billionrowchallenge-1.0-SNAPSHOT.jar dev.noah.Main'

# Function to kill the Java process
kill_java_process() {
    echo "Terminating the Java process..."
    kill $pid
    wait $pid 2>/dev/null # Suppress error output
    exit 1
}

# Trap termination signals and call the function to kill the Java process
trap kill_java_process SIGINT SIGTERM

# Start the Java process
$java_command &

# Get the process ID (PID) of the started process
pid=$!

# Specify the output file name format
output_file_format="profile.html"

sleep 1

# Monitor the process using asprof and save the output to a file
/Users/noah/Downloads/async-profiler-3.0-macos/bin/asprof start $pid -o flamegraph -f $output_file_format &

# Wait for the process to finish
wait $pid
