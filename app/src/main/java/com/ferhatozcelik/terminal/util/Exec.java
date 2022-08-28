package com.ferhatozcelik.terminal.util;

import java.io.FileDescriptor;

public class Exec {
  /**
   * @param cmd
   *          The command to execute
   * @param arg0
   *          The first argument to the command, may be null
   * @param arg1
   *          the second argument to the command, may be null
   * @return the file descriptor of the started process.
   *
   */
  public static FileDescriptor createSubprocess(String cmd, String arg0, String arg1) {
    return createSubprocess(cmd, arg0, arg1, null);
  }

  /**
   * @param cmd
   *          The command to execute
   * @param arg0
   *          The first argument to the command, may be null
   * @param arg1
   *          the second argument to the command, may be null
   * @param processId
   *          A one-element array to which the process ID of the started process will be written.
   * @return the file descriptor of the started process.
   *
   */
  public static native FileDescriptor createSubprocess(String cmd, String arg0, String arg1,
      int[] processId);

  public static native void setPtyWindowSize(FileDescriptor fd, int row, int col, int xpixel,
      int ypixel);

  /**
   * Causes the calling thread to wait for the process associated with the receiver to finish
   * executing.
   *
   * @return The exit value of the Process being waited on
   *
   */
  public static native int waitFor(int processId);

  static {
    System.loadLibrary("com_google_ase_Exec");
  }
}
