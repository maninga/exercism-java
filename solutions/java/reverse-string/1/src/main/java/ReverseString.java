class ReverseString {

    String reverse(String inputString) {
        char[] chArray = inputString.toCharArray();
        for (int head=0; head<chArray.length/2; head++) {
            int tail = chArray.length - head - 1;
            char temp = chArray[head];
            chArray[head] = chArray[tail];
            chArray[tail] = temp;
        }
        return String.valueOf(chArray);
        // or the simpler one
        // return new String(chArray);
    }
  
}
