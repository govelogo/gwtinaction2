package com.manning.gwtia.ch07.shared;

/**
 * An exception that is passed back to the client for any Twitter
 * related exception.
 */
public class GTwitterException extends Exception
{
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 361782417319134919L;

  
  /**
   * Instantiates a new exception.
   */
  public GTwitterException() {
  }

  /**
   * Instantiates a new exception.
   *
   * @param reason the reason
   */
  public GTwitterException(String reason) {
    super(reason);
  }
}
