import java.io.*;
import java.util.*;
// Convinced the input is broken.
class Main {
	private static char STRIKE = 'X';
	private static char SPARE  = '/';

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
		while (true) {
			String line = in.readLine();
			if (line.equals("Game Over")) break;

			// System.out.println(line);

			String[] frames = line.replaceAll("\\s+", " ").split(" ");

			out.append(String.format("%d%n", solve(frames)));
		}

		System.out.print(out);
	}

	private static int solve(final String[] frames) {
		int currentFrame;
		int totalPtr;
		int[] total = new int[11];

		for (totalPtr = 1, currentFrame = 0; totalPtr < 10; ++totalPtr) {
			char frame = frames[currentFrame].charAt(0);
			int frameValue = 0;

			if (!Character.isDigit(frame)) { // 'X'
				// System.out.printf("[frame %d | Index %d | Score %d]: %c - Strike%n", totalPtr, currentFrame, total[totalPtr-1], frame);
				frameValue = 10;

				if (checkStrikes(currentFrame-1, frames, total, totalPtr-1, frameValue)) {
					if (checkStrikes(currentFrame-2, frames, total, totalPtr-2, frameValue)) {
						checkStrikes(currentFrame-1, frames, total, totalPtr-1, frameValue);
					}
				}
				else {
					checkSpare(currentFrame-1, frames, total, totalPtr-1, frameValue);
				}

				++currentFrame;
			}
			else {
				char peek = ' ';
				if (currentFrame+1 < frames.length) {
					peek = frames[currentFrame+1].charAt(0);
				}

				if (!Character.isDigit(peek) && !Character.isWhitespace(peek)) { // '/'
					// System.out.printf("[frame %d | Index %d | Score %d]: %c - Spare%n", totalPtr, currentFrame, total[totalPtr-1], frame);
					frameValue = (int) frame - '0';

					if (checkStrikes(currentFrame-1, frames, total, totalPtr-1, 10)) {
						if (checkStrikes(currentFrame-2, frames, total, totalPtr-2, frameValue)) {
							checkStrikes(currentFrame-1, frames, total, totalPtr-1, frameValue);
						}
					}
					else {
						checkSpare(currentFrame-1, frames, total, totalPtr-1, frameValue);
					}

					frameValue = 10;
				}
				else if (!Character.isWhitespace(peek)) { // Open
					// System.out.printf("[frame %d | Index %d | Score %d]: %c %c - Open%n", totalPtr, currentFrame, total[totalPtr-1], frame, peek);
					int otherFrameValue = (int) peek - '0';
					frameValue = ((int) (frame - '0'));

					if (checkStrikes(currentFrame-1, frames, total, totalPtr-1, frameValue+otherFrameValue)) {
						if (checkStrikes(currentFrame-2, frames, total, totalPtr-2, frameValue)) {
							checkStrikes(currentFrame-1, frames, total, totalPtr-1, frameValue);
						}
					}
					else {
						checkSpare(currentFrame-1, frames, total, totalPtr-1, frameValue);
					}

					frameValue += otherFrameValue;
				}

				++currentFrame; ++currentFrame;
			}

			int previousTotal = total[totalPtr-1];
			total[totalPtr] = (previousTotal + frameValue);
		}

		// TENTH FRAME.
		if ((frames.length - currentFrame) >= 3) {
			char left = frames[currentFrame].charAt(0);
			char middle = frames[currentFrame+1].charAt(0);
			char right = frames[currentFrame+2].charAt(0);
			// System.out.printf("%c %c %c%n", left, middle, right);

			if (checkStrikes(currentFrame, frames, total, totalPtr, 10)) {
				// X ? ?

				// Update previous scores.
				if (checkStrikes(currentFrame-1, frames, total, totalPtr-1, 10)) {
					if (checkStrikes(currentFrame-2, frames, total, totalPtr-2, 10)) {
						checkStrikes(currentFrame-1, frames, total, totalPtr-1, 10);
					}
				}
				else {
					checkSpare(currentFrame-1, frames, total, totalPtr-1, 10);
				}

				if (checkStrikes(currentFrame+1, frames, total, totalPtr, 10)) {
					// X X ?
					
					// Update previous scores.
					checkStrikes(currentFrame-1, frames, total, totalPtr-1, 10);

					if (!checkStrikes(currentFrame+2, frames, total, totalPtr, 10)) {
						// X X -
						int rightValue = (int) (right - '0');

						total[totalPtr] += rightValue;
					}

					// else { total[totalPtr] += 10 }
				}
				else {
					if (!checkSpare(currentFrame+2, frames, total, totalPtr, 10)) {
						// X - -
						int middleValue = (int) (middle - '0');
						int rightValue = (int) (right - '0');

						total[totalPtr] += (rightValue + middleValue);
					}
				}
			}
			else {
				// - / ?
				// System.out.println("- / ?");
				int leftValue = (int) (left - '0');

				total[totalPtr] += 10;

				// Update previous score.
				if (!checkStrikes(currentFrame-1, frames, total, totalPtr-1, 10)) {
					checkSpare(currentFrame-1, frames, total, totalPtr-1, leftValue);
				}
				else {
					if (checkStrikes(currentFrame-2, frames, total, totalPtr-2, leftValue)) {
						checkStrikes(currentFrame-1, frames, total, totalPtr-1, leftValue);
					}
				}

				if (!checkStrikes(currentFrame+2, frames, total, totalPtr, 10)) {
					int rightValue = (int) (right - '0');

					total[totalPtr] += rightValue;
				}
			}
		}
		else {
			int left = (int) (frames[currentFrame].charAt(0) - '0');
			int right = (int) (frames[currentFrame+1].charAt(0) - '0');
			int tenthFrameTotal = left + right;

			if (checkStrikes(currentFrame-1, frames, total, totalPtr-1, tenthFrameTotal)) {
				if (checkStrikes(currentFrame-2, frames, total, totalPtr-2, left)) {
					checkStrikes(currentFrame-1, frames, total, totalPtr-1, left);
				}
			}
			else {
				checkSpare(currentFrame-1, frames, total, totalPtr-1, left);
			}

			total[totalPtr] += (left + right);
		}

		total[totalPtr] += total[totalPtr-1];

		// System.out.println(Arrays.toString(total));

		return total[10];
	}

	private static boolean checkStrikes(int frame, final String[] frames, int[] score, int scorePtr, int toAdd) {
		if (frame > -1) {
			if (frames[frame].charAt(0) == STRIKE) {
				score[scorePtr] += toAdd;
				return true;
			}
		}

		return false;
	}

	private static boolean checkSpare(int frame, final String[] frames, int[] score, int scorePtr, int toAdd) {
		if (frame > -1) {
			if (frames[frame].charAt(0) == SPARE) {
				score[scorePtr] += toAdd;
				return true;
			}
		}

		return false;
	}
}