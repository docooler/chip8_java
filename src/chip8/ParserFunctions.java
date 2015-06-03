package chip8;

public class ParserFunctions {
	public static byte GetY(char instruction)
	{
		return (byte) (instruction >> 4 & 0x0f);
	}

	public static byte GetX(char instruction)
	{
		return (byte) (instruction >> 8 & 0x0f);
	}

	public static byte GetValue(char instruction)
	{
		return (byte) (instruction & 0xff);
	}

	public static byte GetSubCommand(char instruction)
	{
		return (byte)(instruction & 0x0f);
	}

	public static byte GetOpCode(char instruction)
	{
		return (byte) (instruction >> 12);
	}

	public static char GetAddress(char instruction)
	{
		return (char) (instruction & 0x7ff);
	}

	public static ParseResult ParseInstruction(char instruction){
		ParseResult parseResult = new ParseResult();
		parseResult.OpCode = GetOpCode(instruction);
		parseResult.Address = GetAddress(instruction);
		parseResult.Value = GetValue(instruction);
		parseResult.X = GetX(instruction);
		parseResult.Y = GetY(instruction);
		parseResult.SubCommand = GetSubCommand(instruction);
		return parseResult;
	}
}
