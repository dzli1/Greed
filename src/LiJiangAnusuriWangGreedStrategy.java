
public class LiJiangAnusuriWangGreedStrategy extends GreedStrategy {
	public int choose(GreedOption[] options, int[] dice, int bank) {
		int endIndex=0;
		int rollIndex=0;
		int maxScoreIndex=0;
		int temp=0, maxScore=Integer.MIN_VALUE;
		for (int i=0;i<options.length;i++) {
			if (options[i].optionType()==2) {
				if (((ScoringCombination)options[i]).getValue()>100){
					return i;
				}
				temp=((ScoringCombination)options[i]).getValue();
				if (temp>maxScore) {
					maxScore=temp;
					maxScoreIndex=i;
				}
				
			}
			if (options[i].optionType()==1) {
				rollIndex=i;
			}
			if (options[i].optionType()==0) {
				endIndex=i;
			}
		}
		int totalValue=0;
		int scoringDice=0;
		for (int i=0;i<dice.length;i++) {
			if (dice[i]==1) {
				totalValue+=100;
				scoringDice++;
			}
			if (dice[i]==5) {
				totalValue+=50;
				scoringDice++;
			}
		}
		if (scoringDice==dice.length) {
			return maxScoreIndex;
		}
		else if (dice.length!=3) {
			if ((totalValue>100&&dice.length!=6)) {
				return maxScoreIndex;
			}
			else if (dice.length==6&&bank<20000||(dice.length==5&&bank<2500)
			||(dice.length==4&&(bank<600))||(dice.length==2&&bank<=200)
			||(dice.length==1&&bank<=100)) {
				return rollIndex;
			}
			else if (maxScore>0) {
				return maxScoreIndex;
			}
			else {
				return endIndex;
			}
		}
		else {
			if (maxScore>50||(maxScore>0&&(totalValue+bank)>=300)) {
				return maxScoreIndex;
			}
			else if (bank<450) {
				return rollIndex;
			}
			else return endIndex;
		}	
	}
	public String playerName(){
		return "LiJiangAnusuriWangGreedStrategy";
	}
	public String author(){
		return "David Li, Emil Jiang, Rishi Anusuri, Bill Wang";
	}
}


