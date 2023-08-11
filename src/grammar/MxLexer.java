package src.grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MxLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Add=1, Sub=2, Mul=3, Div=4, Mod=5, GreaterThan=6, LesserThan=7, GreaterEqual=8, 
		LesserEqual=9, NotEqual=10, EqualEqual=11, LAnd=12, LOr=13, LNot=14, RightShift=15, 
		LeftShift=16, BAnd=17, BOr=18, BXor=19, BNot=20, Assign=21, SelfdoubleAdd=22, 
		SelfdoubleSub=23, Member=24, LeftmidParen=25, RightmidParen=26, LeftParen=27, 
		RightParen=28, Semi=29, Comma=30, LeftbigParen=31, RightbigParen=32, Questionmark=33, 
		Colon=34, Quote=35, Arrow=36, Void=37, Bool=38, Int=39, String=40, New=41, 
		Class=42, Null=43, True=44, False=45, This=46, If=47, Else=48, For=49, 
		While=50, Break=51, Continue=52, Return=53, Identifier=54, IntConst=55, 
		StringConst=56, BoolConst=57, WhiteSpace=58, BlockComment=59, LineComment=60;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"Add", "Sub", "Mul", "Div", "Mod", "GreaterThan", "LesserThan", "GreaterEqual", 
			"LesserEqual", "NotEqual", "EqualEqual", "LAnd", "LOr", "LNot", "RightShift", 
			"LeftShift", "BAnd", "BOr", "BXor", "BNot", "Assign", "SelfdoubleAdd", 
			"SelfdoubleSub", "Member", "LeftmidParen", "RightmidParen", "LeftParen", 
			"RightParen", "Semi", "Comma", "LeftbigParen", "RightbigParen", "Questionmark", 
			"Colon", "Quote", "Arrow", "Void", "Bool", "Int", "String", "New", "Class", 
			"Null", "True", "False", "This", "If", "Else", "For", "While", "Break", 
			"Continue", "Return", "Identifier", "IntConst", "StringConst", "BoolConst", 
			"EscapeSequence", "WhiteSpace", "BlockComment", "LineComment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'<'", "'>='", "'<='", 
			"'!='", "'=='", "'&&'", "'||'", "'!'", "'>>'", "'<<'", "'&'", "'|'", 
			"'^'", "'~'", "'='", "'++'", "'--'", "'.'", "'['", "']'", "'('", "')'", 
			"';'", "','", "'{'", "'}'", "'?'", "':'", "'\"'", "'->'", "'void'", "'bool'", 
			"'int'", "'string'", "'new'", "'class'", "'null'", "'true'", "'false'", 
			"'this'", "'if'", "'else'", "'for'", "'while'", "'break'", "'continue'", 
			"'return'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Add", "Sub", "Mul", "Div", "Mod", "GreaterThan", "LesserThan", 
			"GreaterEqual", "LesserEqual", "NotEqual", "EqualEqual", "LAnd", "LOr", 
			"LNot", "RightShift", "LeftShift", "BAnd", "BOr", "BXor", "BNot", "Assign", 
			"SelfdoubleAdd", "SelfdoubleSub", "Member", "LeftmidParen", "RightmidParen", 
			"LeftParen", "RightParen", "Semi", "Comma", "LeftbigParen", "RightbigParen", 
			"Questionmark", "Colon", "Quote", "Arrow", "Void", "Bool", "Int", "String", 
			"New", "Class", "Null", "True", "False", "This", "If", "Else", "For", 
			"While", "Break", "Continue", "Return", "Identifier", "IntConst", "StringConst", 
			"BoolConst", "WhiteSpace", "BlockComment", "LineComment"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public MxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MxLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000<\u0171\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007"+
		":\u0002;\u0007;\u0002<\u0007<\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f"+
		"\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c"+
		"\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f"+
		"\u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001%\u0001%\u0001"+
		"&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'"+
		"\u0001\'\u0001(\u0001(\u0001(\u0001(\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0001*\u0001*\u0001*\u0001*\u0001*\u0001+\u0001+\u0001+\u0001"+
		"+\u0001+\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001-\u0001-\u0001"+
		"-\u0001-\u0001-\u0001.\u0001.\u0001.\u0001/\u0001/\u0001/\u0001/\u0001"+
		"/\u00010\u00010\u00010\u00010\u00011\u00011\u00011\u00011\u00011\u0001"+
		"1\u00012\u00012\u00012\u00012\u00012\u00012\u00013\u00013\u00013\u0001"+
		"3\u00013\u00013\u00013\u00013\u00013\u00014\u00014\u00014\u00014\u0001"+
		"4\u00014\u00014\u00015\u00015\u00055\u012d\b5\n5\f5\u0130\t5\u00016\u0001"+
		"6\u00056\u0134\b6\n6\f6\u0137\t6\u00016\u00036\u013a\b6\u00017\u00017"+
		"\u00017\u00057\u013f\b7\n7\f7\u0142\t7\u00017\u00017\u00018\u00018\u0003"+
		"8\u0148\b8\u00019\u00019\u00019\u00019\u00019\u00019\u00039\u0150\b9\u0001"+
		":\u0004:\u0153\b:\u000b:\f:\u0154\u0001:\u0001:\u0001;\u0001;\u0001;\u0001"+
		";\u0005;\u015d\b;\n;\f;\u0160\t;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001"+
		"<\u0001<\u0001<\u0001<\u0005<\u016b\b<\n<\f<\u016e\t<\u0001<\u0001<\u0002"+
		"\u0140\u015e\u0000=\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t"+
		"\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f"+
		"\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014"+
		")\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e"+
		"=\u001f? A!C\"E#G$I%K&M\'O(Q)S*U+W,Y-[.]/_0a1c2e3g4i5k6m7o8q9s\u0000u"+
		":w;y<\u0001\u0000\u0007\u0002\u0000AZaz\u0004\u000009AZ__az\u0001\u0000"+
		"19\u0001\u000009\u0001\u0000 ~\u0003\u0000\t\n\r\r  \u0002\u0000\n\n\r"+
		"\r\u017a\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000"+
		"\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000"+
		"\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000"+
		"\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000"+
		"\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000"+
		"\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000"+
		"\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000"+
		"\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000"+
		"!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001"+
		"\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000"+
		"\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000"+
		"\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00003"+
		"\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001\u0000"+
		"\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000\u0000"+
		"\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000A"+
		"\u0001\u0000\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0000E\u0001\u0000"+
		"\u0000\u0000\u0000G\u0001\u0000\u0000\u0000\u0000I\u0001\u0000\u0000\u0000"+
		"\u0000K\u0001\u0000\u0000\u0000\u0000M\u0001\u0000\u0000\u0000\u0000O"+
		"\u0001\u0000\u0000\u0000\u0000Q\u0001\u0000\u0000\u0000\u0000S\u0001\u0000"+
		"\u0000\u0000\u0000U\u0001\u0000\u0000\u0000\u0000W\u0001\u0000\u0000\u0000"+
		"\u0000Y\u0001\u0000\u0000\u0000\u0000[\u0001\u0000\u0000\u0000\u0000]"+
		"\u0001\u0000\u0000\u0000\u0000_\u0001\u0000\u0000\u0000\u0000a\u0001\u0000"+
		"\u0000\u0000\u0000c\u0001\u0000\u0000\u0000\u0000e\u0001\u0000\u0000\u0000"+
		"\u0000g\u0001\u0000\u0000\u0000\u0000i\u0001\u0000\u0000\u0000\u0000k"+
		"\u0001\u0000\u0000\u0000\u0000m\u0001\u0000\u0000\u0000\u0000o\u0001\u0000"+
		"\u0000\u0000\u0000q\u0001\u0000\u0000\u0000\u0000u\u0001\u0000\u0000\u0000"+
		"\u0000w\u0001\u0000\u0000\u0000\u0000y\u0001\u0000\u0000\u0000\u0001{"+
		"\u0001\u0000\u0000\u0000\u0003}\u0001\u0000\u0000\u0000\u0005\u007f\u0001"+
		"\u0000\u0000\u0000\u0007\u0081\u0001\u0000\u0000\u0000\t\u0083\u0001\u0000"+
		"\u0000\u0000\u000b\u0085\u0001\u0000\u0000\u0000\r\u0087\u0001\u0000\u0000"+
		"\u0000\u000f\u0089\u0001\u0000\u0000\u0000\u0011\u008c\u0001\u0000\u0000"+
		"\u0000\u0013\u008f\u0001\u0000\u0000\u0000\u0015\u0092\u0001\u0000\u0000"+
		"\u0000\u0017\u0095\u0001\u0000\u0000\u0000\u0019\u0098\u0001\u0000\u0000"+
		"\u0000\u001b\u009b\u0001\u0000\u0000\u0000\u001d\u009d\u0001\u0000\u0000"+
		"\u0000\u001f\u00a0\u0001\u0000\u0000\u0000!\u00a3\u0001\u0000\u0000\u0000"+
		"#\u00a5\u0001\u0000\u0000\u0000%\u00a7\u0001\u0000\u0000\u0000\'\u00a9"+
		"\u0001\u0000\u0000\u0000)\u00ab\u0001\u0000\u0000\u0000+\u00ad\u0001\u0000"+
		"\u0000\u0000-\u00b0\u0001\u0000\u0000\u0000/\u00b3\u0001\u0000\u0000\u0000"+
		"1\u00b5\u0001\u0000\u0000\u00003\u00b7\u0001\u0000\u0000\u00005\u00b9"+
		"\u0001\u0000\u0000\u00007\u00bb\u0001\u0000\u0000\u00009\u00bd\u0001\u0000"+
		"\u0000\u0000;\u00bf\u0001\u0000\u0000\u0000=\u00c1\u0001\u0000\u0000\u0000"+
		"?\u00c3\u0001\u0000\u0000\u0000A\u00c5\u0001\u0000\u0000\u0000C\u00c7"+
		"\u0001\u0000\u0000\u0000E\u00c9\u0001\u0000\u0000\u0000G\u00cb\u0001\u0000"+
		"\u0000\u0000I\u00ce\u0001\u0000\u0000\u0000K\u00d3\u0001\u0000\u0000\u0000"+
		"M\u00d8\u0001\u0000\u0000\u0000O\u00dc\u0001\u0000\u0000\u0000Q\u00e3"+
		"\u0001\u0000\u0000\u0000S\u00e7\u0001\u0000\u0000\u0000U\u00ed\u0001\u0000"+
		"\u0000\u0000W\u00f2\u0001\u0000\u0000\u0000Y\u00f7\u0001\u0000\u0000\u0000"+
		"[\u00fd\u0001\u0000\u0000\u0000]\u0102\u0001\u0000\u0000\u0000_\u0105"+
		"\u0001\u0000\u0000\u0000a\u010a\u0001\u0000\u0000\u0000c\u010e\u0001\u0000"+
		"\u0000\u0000e\u0114\u0001\u0000\u0000\u0000g\u011a\u0001\u0000\u0000\u0000"+
		"i\u0123\u0001\u0000\u0000\u0000k\u012a\u0001\u0000\u0000\u0000m\u0139"+
		"\u0001\u0000\u0000\u0000o\u013b\u0001\u0000\u0000\u0000q\u0147\u0001\u0000"+
		"\u0000\u0000s\u014f\u0001\u0000\u0000\u0000u\u0152\u0001\u0000\u0000\u0000"+
		"w\u0158\u0001\u0000\u0000\u0000y\u0166\u0001\u0000\u0000\u0000{|\u0005"+
		"+\u0000\u0000|\u0002\u0001\u0000\u0000\u0000}~\u0005-\u0000\u0000~\u0004"+
		"\u0001\u0000\u0000\u0000\u007f\u0080\u0005*\u0000\u0000\u0080\u0006\u0001"+
		"\u0000\u0000\u0000\u0081\u0082\u0005/\u0000\u0000\u0082\b\u0001\u0000"+
		"\u0000\u0000\u0083\u0084\u0005%\u0000\u0000\u0084\n\u0001\u0000\u0000"+
		"\u0000\u0085\u0086\u0005>\u0000\u0000\u0086\f\u0001\u0000\u0000\u0000"+
		"\u0087\u0088\u0005<\u0000\u0000\u0088\u000e\u0001\u0000\u0000\u0000\u0089"+
		"\u008a\u0005>\u0000\u0000\u008a\u008b\u0005=\u0000\u0000\u008b\u0010\u0001"+
		"\u0000\u0000\u0000\u008c\u008d\u0005<\u0000\u0000\u008d\u008e\u0005=\u0000"+
		"\u0000\u008e\u0012\u0001\u0000\u0000\u0000\u008f\u0090\u0005!\u0000\u0000"+
		"\u0090\u0091\u0005=\u0000\u0000\u0091\u0014\u0001\u0000\u0000\u0000\u0092"+
		"\u0093\u0005=\u0000\u0000\u0093\u0094\u0005=\u0000\u0000\u0094\u0016\u0001"+
		"\u0000\u0000\u0000\u0095\u0096\u0005&\u0000\u0000\u0096\u0097\u0005&\u0000"+
		"\u0000\u0097\u0018\u0001\u0000\u0000\u0000\u0098\u0099\u0005|\u0000\u0000"+
		"\u0099\u009a\u0005|\u0000\u0000\u009a\u001a\u0001\u0000\u0000\u0000\u009b"+
		"\u009c\u0005!\u0000\u0000\u009c\u001c\u0001\u0000\u0000\u0000\u009d\u009e"+
		"\u0005>\u0000\u0000\u009e\u009f\u0005>\u0000\u0000\u009f\u001e\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a1\u0005<\u0000\u0000\u00a1\u00a2\u0005<\u0000\u0000"+
		"\u00a2 \u0001\u0000\u0000\u0000\u00a3\u00a4\u0005&\u0000\u0000\u00a4\""+
		"\u0001\u0000\u0000\u0000\u00a5\u00a6\u0005|\u0000\u0000\u00a6$\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a8\u0005^\u0000\u0000\u00a8&\u0001\u0000\u0000\u0000"+
		"\u00a9\u00aa\u0005~\u0000\u0000\u00aa(\u0001\u0000\u0000\u0000\u00ab\u00ac"+
		"\u0005=\u0000\u0000\u00ac*\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005+"+
		"\u0000\u0000\u00ae\u00af\u0005+\u0000\u0000\u00af,\u0001\u0000\u0000\u0000"+
		"\u00b0\u00b1\u0005-\u0000\u0000\u00b1\u00b2\u0005-\u0000\u0000\u00b2."+
		"\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005.\u0000\u0000\u00b40\u0001\u0000"+
		"\u0000\u0000\u00b5\u00b6\u0005[\u0000\u0000\u00b62\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b8\u0005]\u0000\u0000\u00b84\u0001\u0000\u0000\u0000\u00b9\u00ba"+
		"\u0005(\u0000\u0000\u00ba6\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005)"+
		"\u0000\u0000\u00bc8\u0001\u0000\u0000\u0000\u00bd\u00be\u0005;\u0000\u0000"+
		"\u00be:\u0001\u0000\u0000\u0000\u00bf\u00c0\u0005,\u0000\u0000\u00c0<"+
		"\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005{\u0000\u0000\u00c2>\u0001\u0000"+
		"\u0000\u0000\u00c3\u00c4\u0005}\u0000\u0000\u00c4@\u0001\u0000\u0000\u0000"+
		"\u00c5\u00c6\u0005?\u0000\u0000\u00c6B\u0001\u0000\u0000\u0000\u00c7\u00c8"+
		"\u0005:\u0000\u0000\u00c8D\u0001\u0000\u0000\u0000\u00c9\u00ca\u0005\""+
		"\u0000\u0000\u00caF\u0001\u0000\u0000\u0000\u00cb\u00cc\u0005-\u0000\u0000"+
		"\u00cc\u00cd\u0005>\u0000\u0000\u00cdH\u0001\u0000\u0000\u0000\u00ce\u00cf"+
		"\u0005v\u0000\u0000\u00cf\u00d0\u0005o\u0000\u0000\u00d0\u00d1\u0005i"+
		"\u0000\u0000\u00d1\u00d2\u0005d\u0000\u0000\u00d2J\u0001\u0000\u0000\u0000"+
		"\u00d3\u00d4\u0005b\u0000\u0000\u00d4\u00d5\u0005o\u0000\u0000\u00d5\u00d6"+
		"\u0005o\u0000\u0000\u00d6\u00d7\u0005l\u0000\u0000\u00d7L\u0001\u0000"+
		"\u0000\u0000\u00d8\u00d9\u0005i\u0000\u0000\u00d9\u00da\u0005n\u0000\u0000"+
		"\u00da\u00db\u0005t\u0000\u0000\u00dbN\u0001\u0000\u0000\u0000\u00dc\u00dd"+
		"\u0005s\u0000\u0000\u00dd\u00de\u0005t\u0000\u0000\u00de\u00df\u0005r"+
		"\u0000\u0000\u00df\u00e0\u0005i\u0000\u0000\u00e0\u00e1\u0005n\u0000\u0000"+
		"\u00e1\u00e2\u0005g\u0000\u0000\u00e2P\u0001\u0000\u0000\u0000\u00e3\u00e4"+
		"\u0005n\u0000\u0000\u00e4\u00e5\u0005e\u0000\u0000\u00e5\u00e6\u0005w"+
		"\u0000\u0000\u00e6R\u0001\u0000\u0000\u0000\u00e7\u00e8\u0005c\u0000\u0000"+
		"\u00e8\u00e9\u0005l\u0000\u0000\u00e9\u00ea\u0005a\u0000\u0000\u00ea\u00eb"+
		"\u0005s\u0000\u0000\u00eb\u00ec\u0005s\u0000\u0000\u00ecT\u0001\u0000"+
		"\u0000\u0000\u00ed\u00ee\u0005n\u0000\u0000\u00ee\u00ef\u0005u\u0000\u0000"+
		"\u00ef\u00f0\u0005l\u0000\u0000\u00f0\u00f1\u0005l\u0000\u0000\u00f1V"+
		"\u0001\u0000\u0000\u0000\u00f2\u00f3\u0005t\u0000\u0000\u00f3\u00f4\u0005"+
		"r\u0000\u0000\u00f4\u00f5\u0005u\u0000\u0000\u00f5\u00f6\u0005e\u0000"+
		"\u0000\u00f6X\u0001\u0000\u0000\u0000\u00f7\u00f8\u0005f\u0000\u0000\u00f8"+
		"\u00f9\u0005a\u0000\u0000\u00f9\u00fa\u0005l\u0000\u0000\u00fa\u00fb\u0005"+
		"s\u0000\u0000\u00fb\u00fc\u0005e\u0000\u0000\u00fcZ\u0001\u0000\u0000"+
		"\u0000\u00fd\u00fe\u0005t\u0000\u0000\u00fe\u00ff\u0005h\u0000\u0000\u00ff"+
		"\u0100\u0005i\u0000\u0000\u0100\u0101\u0005s\u0000\u0000\u0101\\\u0001"+
		"\u0000\u0000\u0000\u0102\u0103\u0005i\u0000\u0000\u0103\u0104\u0005f\u0000"+
		"\u0000\u0104^\u0001\u0000\u0000\u0000\u0105\u0106\u0005e\u0000\u0000\u0106"+
		"\u0107\u0005l\u0000\u0000\u0107\u0108\u0005s\u0000\u0000\u0108\u0109\u0005"+
		"e\u0000\u0000\u0109`\u0001\u0000\u0000\u0000\u010a\u010b\u0005f\u0000"+
		"\u0000\u010b\u010c\u0005o\u0000\u0000\u010c\u010d\u0005r\u0000\u0000\u010d"+
		"b\u0001\u0000\u0000\u0000\u010e\u010f\u0005w\u0000\u0000\u010f\u0110\u0005"+
		"h\u0000\u0000\u0110\u0111\u0005i\u0000\u0000\u0111\u0112\u0005l\u0000"+
		"\u0000\u0112\u0113\u0005e\u0000\u0000\u0113d\u0001\u0000\u0000\u0000\u0114"+
		"\u0115\u0005b\u0000\u0000\u0115\u0116\u0005r\u0000\u0000\u0116\u0117\u0005"+
		"e\u0000\u0000\u0117\u0118\u0005a\u0000\u0000\u0118\u0119\u0005k\u0000"+
		"\u0000\u0119f\u0001\u0000\u0000\u0000\u011a\u011b\u0005c\u0000\u0000\u011b"+
		"\u011c\u0005o\u0000\u0000\u011c\u011d\u0005n\u0000\u0000\u011d\u011e\u0005"+
		"t\u0000\u0000\u011e\u011f\u0005i\u0000\u0000\u011f\u0120\u0005n\u0000"+
		"\u0000\u0120\u0121\u0005u\u0000\u0000\u0121\u0122\u0005e\u0000\u0000\u0122"+
		"h\u0001\u0000\u0000\u0000\u0123\u0124\u0005r\u0000\u0000\u0124\u0125\u0005"+
		"e\u0000\u0000\u0125\u0126\u0005t\u0000\u0000\u0126\u0127\u0005u\u0000"+
		"\u0000\u0127\u0128\u0005r\u0000\u0000\u0128\u0129\u0005n\u0000\u0000\u0129"+
		"j\u0001\u0000\u0000\u0000\u012a\u012e\u0007\u0000\u0000\u0000\u012b\u012d"+
		"\u0007\u0001\u0000\u0000\u012c\u012b\u0001\u0000\u0000\u0000\u012d\u0130"+
		"\u0001\u0000\u0000\u0000\u012e\u012c\u0001\u0000\u0000\u0000\u012e\u012f"+
		"\u0001\u0000\u0000\u0000\u012fl\u0001\u0000\u0000\u0000\u0130\u012e\u0001"+
		"\u0000\u0000\u0000\u0131\u0135\u0007\u0002\u0000\u0000\u0132\u0134\u0007"+
		"\u0003\u0000\u0000\u0133\u0132\u0001\u0000\u0000\u0000\u0134\u0137\u0001"+
		"\u0000\u0000\u0000\u0135\u0133\u0001\u0000\u0000\u0000\u0135\u0136\u0001"+
		"\u0000\u0000\u0000\u0136\u013a\u0001\u0000\u0000\u0000\u0137\u0135\u0001"+
		"\u0000\u0000\u0000\u0138\u013a\u00050\u0000\u0000\u0139\u0131\u0001\u0000"+
		"\u0000\u0000\u0139\u0138\u0001\u0000\u0000\u0000\u013an\u0001\u0000\u0000"+
		"\u0000\u013b\u0140\u0005\"\u0000\u0000\u013c\u013f\u0003s9\u0000\u013d"+
		"\u013f\u0007\u0004\u0000\u0000\u013e\u013c\u0001\u0000\u0000\u0000\u013e"+
		"\u013d\u0001\u0000\u0000\u0000\u013f\u0142\u0001\u0000\u0000\u0000\u0140"+
		"\u0141\u0001\u0000\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0141"+
		"\u0143\u0001\u0000\u0000\u0000\u0142\u0140\u0001\u0000\u0000\u0000\u0143"+
		"\u0144\u0005\"\u0000\u0000\u0144p\u0001\u0000\u0000\u0000\u0145\u0148"+
		"\u0003W+\u0000\u0146\u0148\u0003Y,\u0000\u0147\u0145\u0001\u0000\u0000"+
		"\u0000\u0147\u0146\u0001\u0000\u0000\u0000\u0148r\u0001\u0000\u0000\u0000"+
		"\u0149\u014a\u0005\\\u0000\u0000\u014a\u0150\u0005n\u0000\u0000\u014b"+
		"\u014c\u0005\\\u0000\u0000\u014c\u0150\u0005\\\u0000\u0000\u014d\u014e"+
		"\u0005\\\u0000\u0000\u014e\u0150\u0005\"\u0000\u0000\u014f\u0149\u0001"+
		"\u0000\u0000\u0000\u014f\u014b\u0001\u0000\u0000\u0000\u014f\u014d\u0001"+
		"\u0000\u0000\u0000\u0150t\u0001\u0000\u0000\u0000\u0151\u0153\u0007\u0005"+
		"\u0000\u0000\u0152\u0151\u0001\u0000\u0000\u0000\u0153\u0154\u0001\u0000"+
		"\u0000\u0000\u0154\u0152\u0001\u0000\u0000\u0000\u0154\u0155\u0001\u0000"+
		"\u0000\u0000\u0155\u0156\u0001\u0000\u0000\u0000\u0156\u0157\u0006:\u0000"+
		"\u0000\u0157v\u0001\u0000\u0000\u0000\u0158\u0159\u0005/\u0000\u0000\u0159"+
		"\u015a\u0005*\u0000\u0000\u015a\u015e\u0001\u0000\u0000\u0000\u015b\u015d"+
		"\t\u0000\u0000\u0000\u015c\u015b\u0001\u0000\u0000\u0000\u015d\u0160\u0001"+
		"\u0000\u0000\u0000\u015e\u015f\u0001\u0000\u0000\u0000\u015e\u015c\u0001"+
		"\u0000\u0000\u0000\u015f\u0161\u0001\u0000\u0000\u0000\u0160\u015e\u0001"+
		"\u0000\u0000\u0000\u0161\u0162\u0005*\u0000\u0000\u0162\u0163\u0005/\u0000"+
		"\u0000\u0163\u0164\u0001\u0000\u0000\u0000\u0164\u0165\u0006;\u0001\u0000"+
		"\u0165x\u0001\u0000\u0000\u0000\u0166\u0167\u0005/\u0000\u0000\u0167\u0168"+
		"\u0005/\u0000\u0000\u0168\u016c\u0001\u0000\u0000\u0000\u0169\u016b\b"+
		"\u0006\u0000\u0000\u016a\u0169\u0001\u0000\u0000\u0000\u016b\u016e\u0001"+
		"\u0000\u0000\u0000\u016c\u016a\u0001\u0000\u0000\u0000\u016c\u016d\u0001"+
		"\u0000\u0000\u0000\u016d\u016f\u0001\u0000\u0000\u0000\u016e\u016c\u0001"+
		"\u0000\u0000\u0000\u016f\u0170\u0006<\u0001\u0000\u0170z\u0001\u0000\u0000"+
		"\u0000\u000b\u0000\u012e\u0135\u0139\u013e\u0140\u0147\u014f\u0154\u015e"+
		"\u016c\u0002\u0006\u0000\u0000\u0000\u0001\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}