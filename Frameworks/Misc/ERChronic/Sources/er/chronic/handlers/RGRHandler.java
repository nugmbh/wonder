package er.chronic.handlers;

import java.util.LinkedList;
import java.util.List;

import er.chronic.Options;
import er.chronic.utils.Span;
import er.chronic.utils.Token;

public class RGRHandler extends RHandler {

  @Override
  public Span handle(List<Token> tokens, Options options) {
    List<Token> newTokens = new LinkedList<>();
    newTokens.add(tokens.get(1));
    newTokens.add(tokens.get(0));
    newTokens.add(tokens.get(2));
    return super.handle(newTokens, options);
  }
}
