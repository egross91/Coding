import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;

		while ((line = in.readLine()) != null) {
			String[] hand = line.split(" ");

			// Count rules [1-4].
			int handCount = countHand(hand);

			if (isNoTrump(hand, handCount)) {
				out.append("BID NO-TRUMP");
			}
			else {
				handCount += countOtherRules(hand);

				if (handCount < 14) {
					out.append("PASS");
				}
				else {
					out.append("BID ");

					int diamondCount = countSuit('D', hand, "");
					int spadeCount   = countSuit('S', hand, "");
					int heartCount   = countSuit('H', hand, "");
					int clubCount    = countSuit('C', hand, "");
					int maxIndex     = -1;
					int max          = -1;

					List<Integer> counts = new ArrayList<Integer>();
					counts.add(spadeCount);
					counts.add(heartCount);
					counts.add(diamondCount);
					counts.add(clubCount);

					for (int i = 0; i < counts.size(); ++i) {
						int count = counts.get(i);

						if (count > max) {
							max = count;
							maxIndex = i;
						}
					}

					if (maxIndex == 0) {
						out.append("S");
					}
					else if (maxIndex == 1) {
						out.append("H");
					}
					else if (maxIndex == 2) {
						out.append("D");
					}
					else {
						out.append("C");
					}
				}
			}

			out.append("\n");
		}

		System.out.print(out);
	}

	private static int countHand(final String[] hand) {
		int count = 0;

		for (String card : hand) {
			char value = card.charAt(0);
			char suit = card.charAt(1);

			if (value == 'A') { // Ace
				count += 4;
			}
			else if (value == 'K') {
				count += 3;

				if (countSuit(suit, hand, card) == 0) {
					--count;
				}
			}
			else if (value == 'Q') {
				count += 2;

				if (countSuit(suit, hand, card) <= 1) {
					--count;
				}
			}
			else if (value == 'J') {
				++count;

				if (countSuit(suit, hand, card) <= 2) {
					--count;
				}
			}
		}

		return count;
	}

	private static int countOtherRules(final String[] hand) {
		List<Integer> counts = new ArrayList<Integer>();
		int spadeCount       = countSuit('S', hand, "");
		int diamondCount     = countSuit('D', hand, "");
		int clubCount        = countSuit('C', hand, "");
		int heartCount       = countSuit('H', hand, "");

		counts.add(spadeCount);
		counts.add(diamondCount);
		counts.add(clubCount);
		counts.add(heartCount);

		int additionalPoints = 0;

		for (Integer count : counts) {
			if (count <= 1) {
				additionalPoints += 2;
			}
			else if (count == 2) {
				++additionalPoints;
			}
		}

		return additionalPoints;
	}

	private static boolean isNoTrump(final String[] hand, int currentCount) {
		if (currentCount < 16) {
			return false;
		}

		boolean diamondsIsStopped = isStopped('D', hand);
		boolean heartsIsStopped   = isStopped('H', hand);
		boolean clubsIsStopped    = isStopped('C', hand);
		boolean spadesIsStopped   = isStopped('S', hand);

		return (diamondsIsStopped && 
				heartsIsStopped && 
				clubsIsStopped && 
				spadesIsStopped);
	}

	private static boolean isStopped(char suit, final String[] hand) {
		for (String card : hand) {
			char cardSuit  = card.charAt(1);
			char cardValue = card.charAt(0);

			if (cardSuit == suit) {
				if (cardValue == 'A') {
					return true;
				}
				else if (cardValue == 'K') {
					int count = countSuit(suit, hand, card);

					if (count >= 1) {
						return true;
					}
				}
				else if (cardValue == 'Q') {
					int count = countSuit(suit, hand, card);

					if (count >= 2) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private static int countSuit(char suit, final String[] hand, String excluding) {
		int suitCount = 0;

		for (String card : hand) {
			if (!card.equals(excluding)) {
				if (card.charAt(1) == suit) 
					++suitCount;
			}
		}

		return suitCount;
	}
}