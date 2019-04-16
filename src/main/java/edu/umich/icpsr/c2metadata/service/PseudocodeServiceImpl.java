package edu.umich.icpsr.c2metadata.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.c2metadata.sdtl.pojo.Program;
import org.c2metadata.sdtl.pojo.command.TransformBase;
import org.c2metadata.sdtl.pojo.expression.ExpressionBase;
import org.c2metadata.sdtl.pojo.expression.FunctionCallExpression;
import org.c2metadata.sdtl.pojo.expression.GroupedExpression;
import org.c2metadata.sdtl.pojo.expression.StringConstantExpression;
//import org.c2metadata.sdtl.pojo.command.TransformBase.PseudocodeService;
import org.c2metadata.sdtl.pojo.expression.UnknownExpression;
import org.c2metadata.sdtl.pojo.expression.UnsupportedExpression;
//import org.c2metadata.sdtl.pojo.expression.VariableListExpression;
import org.c2metadata.sdtl.pojo.expression.VariableRangeExpression;
//import org.c2metadata.sdtl.pojo.expression.VariableReferenceBase;
import org.c2metadata.sdtl.pojo.expression.VariableSymbolExpression;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.umich.icpsr.commons.dao.FedoraDAO;
import us.mtna.transform.cogs.json.PseudocodeService;

public class PseudocodeServiceImpl implements PseudocodeService {

	private static final Logger LOG = Logger.getLogger(PseudocodeServiceImpl.class);
	
	@Autowired
	@Qualifier("fedoraDAO")
	private FedoraDAO fedoraDAO;

	public String generate(String sdtlString) {
		LOG.info("Executing generate from String input.");
		Program sdtl = convertStringToProgram(sdtlString);
		return generate(sdtl);
	}

	public String generate(Program sdtl) {
		LOG.info("Executing generate from Program input.");
		String output = "";
		for (TransformBase command : sdtl.getCommands()) {
			System.err.println(command.getType());
			output += generate(command);
		}
		return output;
	}

//	public String generate(TransformBase command) {
//		return null;
//	}
	
	
	public String generate(TransformBase command) {
		LOG.info("Executing generate from TransformBase input.");
		String output = "";
		try {
		URL fileUrl = getClass().getResource("/functionLibrary.json");
		File jsFile=new File(fileUrl.getFile());
		FileReader filer= new FileReader(jsFile);
		//ObjectMapper
		byte[] array2 = Files.readAllBytes(jsFile.toPath());
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String,ArrayList<HashMap<String,Object>>> config=mapper.readValue(array2, HashMap.class);
		
		//JSON parser
		JSONParser parser = new JSONParser();
		  BufferedReader read= new BufferedReader(filer);
		  HashMap<String, JSONArray> map= (HashMap<String, JSONArray>)parser.parse(filer);
//		  
//		 ArrayList<HashMap<String,Object>> commandsList=config.get("FunctionLibrary");
//		 for(HashMap<String,Object> map:commandsList) {
//			 
//		 }
		  
		  
		String commandKey =command.getClass().getName();
		  String commType =command.getType();
		
		  JSONArray array =map.get("FunctionLibrary");
		  for(int i=0;i<array.size();i++) {
			  JSONObject obj=(JSONObject)array.get(i);
			 String sdtlNAme= (String)obj.get("SDTLname");
			 if(commType.equalsIgnoreCase(sdtlNAme)) {
				 String[] params=command.getUnknownProperties(); 
			 }
		  }
		}catch(Exception e) {
			LOG.error(e.getMessage(),e);
		}
     
//		if (UnknownTransform.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found UnknownTransform.");
//			output += "UNKNOWN_TRANSFORM";
//		} else if (Unsupported.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found Unsupported.");
//			output += "UNSUPPORTED_TRANSFORM";
//		} else if (Comment.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found Comment.");
//			Comment typedCommand = (Comment) command;
//			output += "Comment: " + typedCommand.getCommentText() + ". ";
//		} else if (Compute.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found Compute.");
//			Compute typedCommand = (Compute) command;
//			output += "Create new variable: " + typedCommand.getVariable() + ". Set to ";
////			output += generate(typedCommand.getExpression());
//			if (typedCommand.getCondition() != null) {
//				output += "if " + generate(typedCommand.getCondition());
//			}
//			output += ". \r";
//		} else if (Delete.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found Delete.");
//			Delete typedCommand = (Delete) command;
//			output += "Delete variable(s): ";
//			String variables = "";
//			if (typedCommand.getVariableRange() != null) {
//				variables += retrieveVariableRange(typedCommand.getVariableRange());
//			}
//			if (typedCommand.getVariables() != null && typedCommand.getVariables().length > 0) {
//				if (!variables.isEmpty()) {
//					variables += ", ";
//				}
//				variables += retrieveVariables(typedCommand.getVariables());
//			}
//			output += variables;
//			output += ". ";
//		} else if (DoIf.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found DoIf.");
//			DoIf typedCommand = (DoIf) command;
//			output += "Execute the following group of commands. ";
//			for (TransformBase transformBase : typedCommand.getCommands()) {
//				output += generate(transformBase);
//			}
//			if (typedCommand.getCondition() != null) {
//				output += "if " + generate(typedCommand.getCondition());
//			}
//		} else if (Execute.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found Execute.");
//			// Execute typedCommand = (Execute) command;
//			output += "Run commands entered since the last execute command.";
//		} else if (Invalid.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found Invalid.");
//			// Invalid typedCommand = (Invalid) command;
//			output += "An invalid command was encountered.";
//		} else if (KeepVariables.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found KeepVariables.");
//			KeepVariables typedCommand = (KeepVariables) command;
//			output += "Keep variable(s): ";
//			String variables = "";
//			if (typedCommand.getVariableRange() != null) {
//				variables += retrieveVariableRange(typedCommand.getVariableRange());
//			}
//			if (typedCommand.getVariables() != null && typedCommand.getVariables().length > 0) {
//				if (!variables.isEmpty()) {
//					variables += ", ";
//				}
//				variables += retrieveVariables(typedCommand.getVariables());
//			}
//			output += variables;
//			output += ". ";
//		} else if (Load.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found Load.");
//			Load typedCommand = (Load) command;
//			output += "Load dataset: " + typedCommand.getFileName() + ". \r";
//		} else if (MergeDatasets.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found MergeDatasets.");
//			MergeDatasets typedCommand = (MergeDatasets) command;
//			output += "Merge files ";
//			for (int i = 0; i < typedCommand.getFileName().length; i++) {
//				if (i != 0) {
//					output += ", ";
//				}
//				output += typedCommand.getFileName()[i];
//			}
//			output += ". ";
//		} else if (Recode.class.isAssignableFrom(command.getClass())) { // DONE,
//																		// but
//																		// there
//																		// seem
//																		// to be
//																		// problems
//																		// with
//																		// the
//																		// java
//																		// object
//																		// model
//			LOG.info("Found Recode.");
//			Recode typedCommand = (Recode) command;
//			output += "Recode ";
//			String recodedVariables = "";
//			if (typedCommand.getRecodedVariables() != null && typedCommand.getRecodedVariables().length > 0) {
//				for (int i = 0; i < typedCommand.getRecodedVariables().length; i++) {
//					RecodeVariable recodeVariable = typedCommand.getRecodedVariables()[i];
//					if (i > 0) {
//						recodedVariables += ", ";
//					}
//					recodedVariables += "variable " + recodeVariable.getSource();
//					if (recodeVariable.getTarget() != null) {
//						recodedVariables += " into new variable " + recodeVariable.getTarget();
//					}
//				}
//			}
//			if (typedCommand.getRecodedVariableRange() != null) {
//				if (!recodedVariables.isEmpty()) {
//					recodedVariables += ", ";
//				}
//				recodedVariables += "variables " + typedCommand.getRecodedVariableRange().getFirst() + " through "
//						+ typedCommand.getRecodedVariableRange().getLast();
//			}
//			output += recodedVariables;
//			output += " ";
//			for (int i = 0; i < typedCommand.getRules().length; i++) {
//				if (i > 0) {
//					output += ", ";
//				}
//				output += retrieveRecodeRule(typedCommand.getRules()[i]);
//			}
//			// TODO George's notes indicate there should be a condition here.
//			output += ". ";
//		} else if (Rename.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found Rename.");
//			Rename typedCommand = (Rename) command;
//			RenamePair[] renamePairs = typedCommand.getRenames();
//			for (RenamePair renamePair : renamePairs) {
//				output += "Rename variable: " + renamePair.getOldName() + " as: " + renamePair.getNewName() + ". \r";
//			}
//		} else if (ReshapeLong.class.isAssignableFrom(command.getClass())) {
//			LOG.info("Found ReshapeLong.");
//			output += "...";
//		} else if (ReshapeWide.class.isAssignableFrom(command.getClass())) {
//			LOG.info("Found ReshapeWide.");
//		} else if (Save.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found Save.");
//			Save typedCommand = (Save) command;
//			output += "Save dataset: " + typedCommand.getFileName() + ". \r";
//		} else if (Select.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found Select.");
//			Select typedCommand = (Select) command;
//			output += "Select cases ";
//			if (typedCommand.getCondition() != null) {
//				output += "if " + generate(typedCommand.getCondition());
//			}
//		} else if (SetDatasetProperty.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found SetDatasetProperty.");
//			SetDatasetProperty typedCommand = (SetDatasetProperty) command;
//			output += "Set dataset property " + typedCommand.getPropertyName() + " to " + typedCommand.getValue()
//					+ ". ";
//		} else if (SetDisplayFormat.class.isAssignableFrom(command.getClass())) {
//			LOG.info("Found SetDisplayFormat.");
//			output += "...";
//		} else if (SetMissingValues.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found SetMissingValues.");
//			SetMissingValues typedCommand = (SetMissingValues) command;
//			output += "Set values to MISSING for ";
//			String variables = "";
//			if (typedCommand.getVariableRange() != null) {
//				variables += retrieveVariableRange(typedCommand.getVariableRange()) + " ";
//			}
//			if (typedCommand.getVariables() != null && typedCommand.getVariables().length > 0) {
//				if (!variables.isEmpty()) {
//					variables += ", ";
//				}
//				variables += retrieveVariables(typedCommand.getVariables());
//			}
//			output += variables;
//			output += " ";
//			output += "when the values are: ";
//			String values = "";
//			if (typedCommand.getValueRange() != null) {
//				values += retrieveValueRange(typedCommand.getValueRange());
//			}
//			if (typedCommand.getValue() != null) {
//				if (!values.isEmpty()) {
//					values += ", ";
//				}
//				values += retrieveValues(typedCommand.getValue());
//			}
//			output += values;
//			output += ". ";
//		} else if (SetValueLabels.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found SetValueLabels.");
//			SetValueLabels typedCommand = (SetValueLabels) command;
//			output += "Label the values of ";
//			output += retrieveVariables(typedCommand.getVariables(), typedCommand.getVariableRange());
//			output += " to ";
//			for (int i = 0; i < typedCommand.getLabels().length; i++) {
//				if (i > 0) {
//					output += ", ";
//					output += typedCommand.getLabels()[i];
//				}
//			}
//			output += ". ";
//		} else if (SetVariableLabel.class.isAssignableFrom(command.getClass())) { // DONE
//			LOG.info("Found SetVariableLabel.");
//			SetVariableLabel typedCommand = (SetVariableLabel) command;
//			output += "Set the label for " + typedCommand.getVariable().getName() + " to \"" + typedCommand.getLabel()
//					+ "\". ";
//		} else {
//			LOG.error("Unrecognized TransformBase = " + command.getClass().getName());
//		}
		return output;
	}
	

	private String retrieveVariables(String[] variables, VariableRangeExpression variableRange) {
		String output = "";
		if (variables != null && variables.length > 0) {
			for (int i = 0; i < variables.length; i++) {
				if (i > 0) {
					output += ", ";
				}
				output += variables[i];
			}
		}
		if (variableRange != null) {
			if (!output.isEmpty()) {
				output += ", ";
			}
			output += variableRange.getLast() + " to " + variableRange.getLast();
		}
		return output;
	}

//	private String retrieveRecodeRule(RecodeRule recodeRule) {
//		String output = "";
//		String fromValues = "";
//		if (recodeRule.getFromValue() != null && recodeRule.getFromValue().length > 0) {
//			for (int i = 0; i < recodeRule.getFromValue().length; i++) {
//				if (i > 0) {
//					fromValues += ", ";
//				}
//				fromValues += recodeRule.getFromValue()[i];
//			}
//		}
//		if (recodeRule.getFromValueRange() != null && recodeRule.getFromValueRange().length > 0) {
//			if (!fromValues.isEmpty()) {
//				fromValues += ", ";
//			}
//			for (int i = 0; i < recodeRule.getFromValueRange().length; i++) {
//				ValueRange valueRange = recodeRule.getFromValueRange()[i];
//				if (i > 0) {
//					fromValues += ", ";
//				}
//				fromValues += valueRange.getFirst() + " through " + valueRange.getLast();
//			}
//		}
//		if (!fromValues.isEmpty() && recodeRule.getTo() != null && !recodeRule.getTo().isEmpty()) {
//			output += "so that " + fromValues + " are coded as " + recodeRule.getTo();
//		}
//		if (recodeRule.getLabel() != null && !recodeRule.getLabel().isEmpty()) {
//			output += " and labeled as " + recodeRule.getLabel();
//		}
//		// TODO How do I handle "special"?
//		// recodeRule.getSpecialFromValue()
//		// recodeRule.getSpecialToValue()
//		return output;
//	}

//	private String retrieveValueRange(ValueRange[] ranges) {
//		String output = "";
//		for (int i = 0; i < ranges.length; i++) {
//			if (i != 0) {
//				output += ", ";
//			}
//			output += ranges[i].getFirst() + " to " + ranges[i].getLast();
//		}
//		return output;
//	}

	private String retrieveValues(String[] values) {
		return Arrays.toString(values).replace("[", "").replace("]", "");
	}

	private String retrieveVariableRange(VariableRangeExpression range) {
		return range.getFirst() + " to " + range.getLast();
	}

//	private String retrieveVariables(VariableReferenceBase[] variables) {
//		// FIXME used to receive String[] and return String
//		// Not sure my fix is what was expected
//		//return Arrays.toString(variables).replace("[", "").replace("]", "");
//		String output = "";
//		for(int i=0;i<variables.length;i++) {
//			if(i>0) {
//				output += ",";
//			}
//			output += variables[i].getName();
//		}
//		return output;
//	}

	private String generate(ExpressionBase expression) {
		LOG.info("Executing generate from ExpressionBase input.");
		String output = "";
		
		if (expression != null) {
			if (UnknownExpression.class.isAssignableFrom(expression.getClass())) {
				LOG.info("Found UnknownExpression.");
				output += "UNKNOWN_EXPRESSION";
			} else if (UnsupportedExpression.class.isAssignableFrom(expression.getClass())) {
				LOG.info("Found UnsupportedExpression.");
				output += "UNSUPPORTED_EXPRESSION";
//			} else if (DoubleConstantExpression.class.isAssignableFrom(expression.getClass())) {
//				LOG.info("Found DoubleConstantExpression.");
//
//				output += "...";
			} else if (GroupedExpression.class.isAssignableFrom(expression.getClass())) {
				LOG.info("Found GroupedExpression.");
				output += "...";
			} else if (FunctionCallExpression.class.isAssignableFrom(expression.getClass())) {
				LOG.info("Found FunctionCallExpression.");

				output += handleFunctionExpression((FunctionCallExpression) expression);
//			} else if (IntConstantExpression.class.isAssignableFrom(expression.getClass())) {
//				LOG.info("Found IntConstantExpression.");
//				IntConstantExpression exp = (IntConstantExpression) expression;
//				output += exp.getIntValue();
			} else if (StringConstantExpression.class.isAssignableFrom(expression.getClass())) {
				LOG.info("Found StringConstantExpression.");
				StringConstantExpression exp = (StringConstantExpression) expression;
				output += exp.getName();
			//} else if (VariableListExpression.class.isAssignableFrom(expression.getClass())) {
			//	LOG.info("Found VariableListExpression.");
			//	output += "...";
			} else if (VariableRangeExpression.class.isAssignableFrom(expression.getClass())) {
				LOG.info("Found VariableRangeExpression.");
				output += "...";
			} else if (VariableSymbolExpression.class.isAssignableFrom(expression.getClass())) {
				LOG.info("Found VariableSymbolExpression.");
				VariableSymbolExpression exp = (VariableSymbolExpression) expression;
				output += exp.getVariableName();
			}
		}
		return output;
	}

	private String handleFunctionExpression(FunctionCallExpression expression) {
		StringBuilder output = new StringBuilder(" ");
		String function = expression.getFunction();	
		
		switch (function) {
		case "absolute_value":
			output.append("Absolute value of ").append(generate(expression.getArguments()[0])).append(". \r");
			break;
		case "addition":
			output.append(generate(expression.getArguments()[0])).append(" + ")
					.append(generate(expression.getArguments()[1])).append(". \r");
			break;
		case "coefficient_of_variation":

			output.append("coefficient of variation for (");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append(")").append(". \r");
			break;
		case "cos":
			output.append("Cosine of ").append(generate(expression.getArguments()[0])).append(". \r");
			break;
		case "division":
			output.append(generate(expression.getArguments()[0])).append("/")
					.append(generate(expression.getArguments()[1])).append(". \r");
			break;
		case "dot_operator":
			output.append("if (");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append(") valid values").append(". \r");
			break;

		case "eq":
			output.append(generate(expression.getArguments()[0])).append(" = ")
					.append(generate(expression.getArguments()[1])).append(". \r");
			break;
		case "exponentiation":
			output.append(generate(expression.getArguments()[0])).append(" raised to the power ")
					.append(generate(expression.getArguments()[1])).append(". \r");
			break;
		case "logarithm_base_10":
			output.append("base 10 log of " + generate(expression.getArguments()[0])).append(". \r");
			break;
		case "max":

			output.append("the maximum value of (");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append(")").append(". \r");
			break;
		case "mean":
			output.append("Mean of ...[(");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append(")]").append(". \r");
			break;
		case "median":
			output.append("the \"within-subjects\" median of (");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append(")").append(". \r");
			break;
		case "power":
			output.append(generate(expression.getArguments()[0])).append(" raised to the power ")
					.append(generate(expression.getArguments()[1])).append(". \r");
			break;

		case "min":
			output.append("the minium value of (");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append(")").append(". \r");
			break;

		case "sine":
			output.append("Sine of ").append(generate(expression.getArguments()[0])).append(". \r");
			break;
		case "square_root":
			output.append("Square root of (").append(generate(expression.getArguments()[0])).append("). \r");
			break;

		case "modulo":
			output.append("Remainder of (").append(generate(expression.getArguments()[0])).append(") divided by ( ")
					.append(generate(expression.getArguments()[1])).append("). \r");
			break;
		case "multiplication":
			output.append(generate(expression.getArguments()[0])).append(" * ")
					.append(generate(expression.getArguments()[1])).append(". \r");
			break;

		case "subtraction":
			output.append("(" + generate(expression.getArguments()[0])).append(" - ")
					.append(generate(expression.getArguments()[1]) + ")").append(". \r");
			break;

		case "natural_logarithm":
			output.append("natural log of ").append(generate(expression.getArguments()[0])).append(". \r");
			break;
		case "standard_deviation":
			LOG.info("standard deviation.");
			output.append("the standard deviation of(");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append(")").append(". \r");
			break;
		case "sum":
			LOG.info("Sum Expression.");
			output.append("sum of [(");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append(")]").append(". \r");
			break;
		case "variance":
			output.append("the \"within-subjects\" variance of [(");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append(")]").append(". \r");
			break;
		case "truncate":
			LOG.info("Truncate Expression.");
			output.append("truncate (");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append(")").append(". \r");
			break;
		case "tangent":

			output.append("Tanget of ");
			output.append(generate(expression.getArguments()[0])).append(". \r");

			break;
		// Adding new code
		case "diff":

			output.append("Code for " + function + " coming soon");

			break;
		case "ends":

			output.append("Code for " + function + " coming soon");

			break;
		case "fill":

			output.append("Code for " + function + " coming soon");

			break;

		case "group":

			output.append("Assigns a group number to one row in groups defined by ");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append(". \r");
			break;
		case "mtr":

			output.append("U.S. marginal income tax rate for a married couple with income  ");
			output.append(generate(expression.getArguments()[0])).append(" in year ")
					.append(generate(expression.getArguments()[1]));

			break;
		case "rowfirst":

			output.append("First nonmissing value of [ ");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append("] for each observation. \r");
			break;
		case "rowlast":

			output.append("Last nonmissing value of [ ");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append("] for each observation. \r");
			break;
		case "rowmiss":

			output.append("Number of missing values in [ ");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append("]. \r");
			break;
		case "rownonmiss":

			output.append("Number of nonmissing values in [ ");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append("]. \r");
			break;
		case "rowpctile":
			output.append("[");
			output.append(generate(expression.getArguments()[0]));
			output.append("] th percentile of [ ");
			for (int i = 1; i < expression.getArguments().length; i++) {
				ExpressionBase exp = expression.getArguments()[i];
				output.append(generate(exp)).append(",");
			}
			output.append("]. \r");
			break;
		case "rowtotal":
			output.append("Code for " + function + " coming soon");

			break;
		case "tag":
			output.append("Code for " + function + " coming soon");

			break;
		
		case "anycount":

		  	output.append("Code for " + function + " coming soon");
			break;
		case "anymatch":

			output.append("Code for " + function + " coming soon");
			break;
		case "anyvalue":
			output.append("Equals the ");
			output.append(generate(expression.getArguments()[0]));
			output.append("if equals a value in [ ");
			for (int i = 1; i < expression.getArguments().length; i++) {
				ExpressionBase exp = expression.getArguments()[i];
				output.append(generate(exp)).append(",");
			}
			output.append("]. \r");
			break;
		case "concat":
			output.append("Creates a string by concatenating [");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append("]. \r");
			break;
		case "cut":
			output.append("Creates a categorical variable from a list of breakpoints [");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			output.append("]. \r");
			break;
		case "std_val":
			output.append("Creates standardized value of").append(generate(expression.getArguments()[0])).append("with mean").append(generate(expression.getArguments()[1])).append(" and standard deviation "+generate(expression.getArguments()[2]));
			
			output.append(". \r");
			break;
		case "missing_value":
			output.append("missing value");
			
			output.append(". \r");
			break;
		case "int":
			output.append("integer value of ")
			.append(generate(expression.getArguments()[0]))
			.append(". \r");
			break;
		case "aggregate":
			
			output.append("create a new dataset with one row per group")
			.append(". \r");
			break;
		case "group_by":
			output.append("groups defined by ")
			.append(generate(expression.getArguments()[0]))
			.append(". \r");
			break;
		
		case "ne":
			output.append("not equals to");
			break;		
		case "lt":
			output.append("less than");
			break;	
		case "le":
			output.append("less than or equal to");
			break;	
		case "gt":
			output.append("greater than");
			
			break;
		case "ge":
			output.append("greater than or equal to");
			
			break;
		case "not":case"and":case "or":
			output.append(" "+function+" ");
			break;
		case "range":
			
			output.append(generate(expression.getArguments()[0])).append(" is within range from ").append(generate(expression.getArguments()[1])).append(" to "+generate(expression.getArguments()[2]));
			
			break;
		case "any":
			
			output.append(generate(expression.getArguments()[0]));
			output.append("mathes ");
			
			for (int i = 1; i < expression.getArguments().length; i++) {
				ExpressionBase exp = expression.getArguments()[i];
				output.append(generate(exp)).append(",");
			}
			output.append(". \r");
			break;
		case "missing_var":	case "missing_num":
			output
			.append(generate(expression.getArguments()[0]))
			.append(" is missing ")
			.append(". \r");
			break;
		case "missing_list":
			output.append("a variable in  ");
			for (ExpressionBase exp : expression.getArguments()) {
				output.append(generate(exp)).append(",");
			}
			
			output.append(" is missing . \r");
			break;
		case "agg_count":
			output.append("Number of nonmissing cases for ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(" is missing . \r");
			break;
			
		case "agg_iqr":
			output.append("Interquartile range of ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;

		case "agg_kurt":
			output.append("Kurtosis  in groups by ")
			.append(generate(expression.getArguments()[0]))
			.append(". \r");
			break;
		case "agg_mad":
			output.append("Median absolute deviation of  ")
			.append(generate(expression.getArguments()[0])).append(" from median  in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
		case "agg_max":
			output.append("Maximum  of  ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
			
		case "agg_mdev":
			output.append("Mean absolute deviation of ")
			.append(generate(expression.getArguments()[0])).append(" from the mean  in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
			
		case "agg_mean":
			output.append("Mean of  ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
		case "agg_median":
			output.append("Median of  ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
		case "agg_min":
			output.append("Minimum of  ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
		case "agg_mode":
			output.append("Mode in groups by ")
			.append(generate(expression.getArguments()[0]))
			.append(". \r");
			break;
		case "agg_pc":
			output.append("Percentage  of  ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
		case "agg_pctile":
			output.append(generate(expression.getArguments()[0]))
			.append("th percentile of ")
			.append(generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
			.append(". \r");
			break;
		case "agg_rank":
			output.append("Ranks  of  ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
		case "agg_sd":
			output.append("Standard deviation of ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
			
		case "agg_seq":
			output.append("Integer sequences from ")
			.append(generate(expression.getArguments()[0])).append(" to ").append(generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
			.append(". \r");
			break;
		case "agg_skew":
			output.append("Skewness in groups by ")
			.append(generate(expression.getArguments()[0]))
			.append(". \r");
			break;
		case "agg_total":
			output.append("Total  of ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
		case "agg_pgt":
			output.append("Percentage greater than ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
		case "agg_plt":
			output.append("Percentage less than ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
		case "agg_pin":
			output.append("Percentage between  ")
			.append(generate(expression.getArguments()[0])).append(" and ").append(generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
			.append(". \r");
			break;
		case "agg_pout":
			output.append("Percentage not between  ")
			.append(generate(expression.getArguments()[0])).append(" and ").append(generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
			.append(". \r");
		case "agg_fgt":
			output.append("Fraction of cases greater than X  for ")
			.append(generate(expression.getArguments()[0])).append(" and ").append(generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
			.append(". \r");
			break;
			
		case "agg_flt":
			output.append("Fraction of cases greater than X  for ")
			.append(generate(expression.getArguments()[0])).append(" and ").append(generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
			.append(". \r");
			break;
		case "agg_fin":
			output.append("Fraction of cases between ")
			.append(generate(expression.getArguments()[0])).append(" and ").append(generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
			.append(". \r");
			break;	
			
		case "agg_fout":
			output.append("Fraction of cases not between ")
			.append(generate(expression.getArguments()[0])).append(" and ").append(generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
			.append(". \r");
			break;	
		case "agg_cgt":
			output.append("Count of cases greater than X  for ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
		case "agg_clt":
			output.append("Count of cases less than X  for ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
		case "agg_cin":
			output.append("Count of cases between ")
			.append(generate(expression.getArguments()[0])).append(" and ").append(generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
			.append(". \r");
			break;
			
		case "agg_cout":
			output.append("Count of cases not between ")
			.append(generate(expression.getArguments()[0])).append(" and ").append(generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
			.append(". \r");
			break;
		case "agg_n":
			output.append("Weighted number of cases  for ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
//		case "agg_count":
//			output.append("Weighted number of cases  for ")
//			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
//			.append(". \r");
//			break;
		case "agg_nmiss":
			output.append("Weighted number of missing cases for ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
		case "agg_numiss":
			output.append("Unweighted  number of missing cases for ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
		case "agg_first":
			output.append("First nonmissing observed value  for  ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
		case "agg_last":
			output.append("Last nonmissing observed value  for  ")
			.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
			.append(". \r");
			break;
		case "collapse":
			output.append("create a new dataset with one row per group  ")
			
			.append(". \r");
			break;
		case "col_count":
			output.append("Number of nonmissing cases in groups by  ")
			.append(generate(expression.getArguments()[0]))
			.append(". \r");
			break;
			case "col_first":
				output.append("First value of in groups by ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_firstnm":
				output.append("First nonmissing value of ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_iqr":
				output.append("Interquartile range of ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_last":
				output.append("Last value of ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_lastnm":
				output.append("Last nonmissing value of ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_max":
				output.append("Maximum  of ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			
			case "col_mean":
				output.append("Mean of ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_median":
				output.append("Median of ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_min":
				output.append("Minimum of ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_pctile":
				output.append(generate(expression.getArguments()[0]))
				.append("th percentile of ")
				.append(generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
				.append(". \r");
				break;
			case "col_sd":
				output.append("Standard deviation of ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_sebinomial":
				output.append("Standard error of the mean ")
				.append(generate(expression.getArguments()[0])).append(" of "+generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
				.append(". \r");
				break;
			case "col_semean":
				output.append("Standard error of the mean of ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_sepoisson":
				output.append("Standard error of the mean ")
				.append(generate(expression.getArguments()[0])).append(" of "+generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
				.append(". \r");
				break;
			case "col_sum":
				output.append("Sum  of ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_pgt":
				output.append("Percentage greater than ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_plt":
				output.append("Percentage less than ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_pin":
				output.append("Percentage between ")
				.append(generate(expression.getArguments()[0])).append(" and "+generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
				.append(". \r");
				break;
			
			case "col_pout":
				output.append("Percentage not between ")
				.append(generate(expression.getArguments()[0])).append(" and "+generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
				.append(". \r");
				break;
			case "col_fgt":
				output.append("Fraction of cases greater than  ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_flt":
				output.append("Fraction of cases less than  ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_fin":
				output.append("Fraction of cases between ")
				.append(generate(expression.getArguments()[0])).append(" and "+generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
				.append(". \r");
				break;
			case "col_fout":
				output.append("Fraction of cases not between ")
				.append(generate(expression.getArguments()[0])).append(" and "+generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
				.append(". \r");
				break;
			case "col_cgt":
				output.append("Count of cases greater than  ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_clt":
				output.append("Count of cases less than  ")
				.append(generate(expression.getArguments()[0])).append(" in groups by ").append(generate(expression.getArguments()[1]))
				.append(". \r");
				break;
			case "col_cin":
				output.append("Count of cases between ")
				.append(generate(expression.getArguments()[0])).append(" and "+generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
				.append(". \r");
				break;
			case "col_cout":
				output.append("Count of cases not between ")
				.append(generate(expression.getArguments()[0])).append(" and "+generate(expression.getArguments()[1])).append(" in groups by ").append(generate(expression.getArguments()[2]))
				.append(". \r");
				break;
			case "col_n":
				output.append("Weighted number of cases in groups by ")
				.append(generate(expression.getArguments()[0]))
				.append(". \r");
				break;
			case "col_nu":
				output.append("Unweighted number of cases in groups by ")
				.append(generate(expression.getArguments()[0]))
				.append(". \r");
				break;
			case "col_nmiss":
				output.append("Weighted number of missing cases in groups by ")
				.append(generate(expression.getArguments()[0]))
				.append(". \r");
				break;
			case "col_numiss":
				output.append("Weighted number of non missing cases in groups by ")
				.append(generate(expression.getArguments()[0]))
				.append(". \r");
				break;
//			case "col_first":
//				output.append("First nonmissing observed value by  ")
//				.append(generate(expression.getArguments()[0]))
//				.append(". \r");
//				break;
//			case "col_last":
//				output.append("Last nonmissing observed value by  ")
//				.append(generate(expression.getArguments()[0]))
//				.append(". \r");
//				break;
			
			
			
		default:
			output.append("Code for " + function + " coming soon");
			break;
		}
		return output.toString();

	}

	private Program convertStringToProgram(String sdtlString) {
		LOG.info("Executing convertStringToProgram.");
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			mapper.findAndRegisterModules();
			Program program = mapper.readValue(sdtlString, Program.class);
			return program;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	
	private HashMap getConfig() {
		try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
	String defaultPath = "/c2metadata/admin/config/functionLibrary.json";
//		File f= new File("/build/awstest.properties");
//		ClassLoader classLoader = getClass().getClassLoader();
//		InputStream inputStream = classLoader.getResourceAsStream("awstest.properties");
		URL fileUrl = getClass().getResource("/functionLibrary.json");
		File jsFile=new File(fileUrl.getFile()); 
		
		FileWriter write= new FileWriter(jsFile);
		write.write("");
		write.close();
//		MultipartFile functionLibraryDoc = multipartRequest.getFile("functionsLibrary");
//		functionLibraryDoc.transferTo(jsFile);
		//FileReader filer= new FileReader(jsFile);
		
//		  JSONParser parser = new JSONParser();
//		  Object object = parser.parse(filer);
//		  JSONObject jsonObject = (JSONObject)object;
	byte[] jsonContent=null;
	ObjectMapper mapper = new ObjectMapper();
fedoraDAO.downloadBinary(defaultPath, bos, true);
		 jsonContent = bos.toByteArray();
//		 HashMap functionJson = mapper.readValue(jsonContent,HashMap.class);
//		 System.err.println(functionJson);
		}catch(Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

}