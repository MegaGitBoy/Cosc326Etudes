##
 #-----------------------------------------------------------------------
 # ======================================================================1
 # Pulses Counting
 # University of Otago, 2021 Semester 2 (New Zealand)
 # Cosc326(Effective Programming):  Ettude-9 (Pulses Counting)
 # @authors: Valentin Kiselev, Leo Venn
 # date: 23 Sep 2021
 # ======================================================================
 #
 # This program counts the number of pulses
 # in a given dataset text file.
 #-----------------------------------------------------------------------

#
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# To start application run this command:
# "python PulseCounting <DATASET_FILE>"
# where <DATASET_FILE> is a text file
# containing list of recorded pulse data.
# This dataset file should contain samples
# of pulses directly collected from a pulses
# sensor. The data in the file should be
# represented as integers in a single column.
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

import math
import sys
import time
import matplotlib.pyplot as plt

#---Class for counting pulses:
class PulseCounter:

    #---Constructor:
    def __init__(self, pulse_data_input, conv_filter, num_of_conv=1):
        '''
        :param pulse_data_input: list of recorded pulse data
        :param conv_filter: list with custom convolution filter
        :param num_of_conv: number of convolutions to be repeated
        '''
        self.pulse_data = pulse_data_input
        self.kernel = conv_filter
        self.number_of_convolutions = num_of_conv
        self.total_peak_count = 0

    # ---Used to normalize original sensor data:
    def normalize(self, original_data_points_):
        '''
        :param original_data_points_: list with original sensor data
        :return: list of normalized data
        '''
        normalized_data_ = []
        total_sum_ = sum(original_data_points_)
        total_points_ = len(original_data_points_)
        mean_val_ = total_sum_ / total_points_
        for i in original_data_points_:
            normalized_data_point_ = i - mean_val_
            normalized_data_.append(normalized_data_point_)
        return normalized_data_

    #---Used to find a dot product between two given vectors:
    def find_dot_product(self, vector_a_, vector_b_):
        '''
        :param vector_a_: first vector
        :param vector_b_: second vector
        :return: a dot product between given two vectors
        '''
        dot_product_ = sum(x * y for x, y in zip(vector_a_, vector_b_))
        return dot_product_

    #---Perform convolution between the kernel filter and the dataset:
    def convolution(self, data_points_, pattern_filter_):
        '''
        :param data_points_: pulse data (normalized)
        :param pattern_filter_: custom kernel filter
        :return: filtered pulse data
        '''
        data_points_filtered_ = []
        kernel_size = len(self.kernel)
        #print("kernel -> size: ", kernel_size)
        for i in range(len(data_points_) - kernel_size):
            sliding_window_ = data_points_[i:i + kernel_size]
            dot_product_ = self.find_dot_product(sliding_window_, pattern_filter_)
            data_points_filtered_.append(dot_product_)
        return data_points_filtered_

    #---Used to count the number of pulses:
    def count_peaks(self, data_ponts_):
        '''
        :param data_ponts_: pulse data (normalized)
        :return: a total number of counted pulses
        '''
        prev_position_ = 0
        current_position_ = 0
        peak_count_ = 0
        for i in range(len(data_ponts_)):
            #print("prev_position_=",prev_position_,"current_position_=",current_position_)
            prev_position_ = current_position_
            current_position_ = data_ponts_[i]
            if ((prev_position_ < 0 and current_position_ >= 0) or (prev_position_ > 0 and current_position_ <= 0)):
                peak_count_ += 1
                #print("peak_count_: ", peak_count_)
        return math.floor(peak_count_ / 2)

    #---Used to plot the pulses before and after the filtering:
    def plot_pulses(self, original_dataset_, filtered_dataset_):
        '''
        :param original_dataset_: list of original recorded pulse data
        :param filtered_dataset_: list of filtered pulse data
        :return:
        '''
        #---Make Horizontally stacked subplots:
        fig, (ax1, ax2) = plt.subplots(1, 2)
        fig.suptitle('Original vs Filtered pulse data\n[Total pulse count = {}]'.format(self.total_peak_count))
        #---Subplot 1 (Originalpulse data):
        ax1.set_title("Original")
        ax1.set_xlabel("Time")
        ax1.set_ylabel("Sensor Readings")
        ax1.plot(original_dataset_)
        #---Subplot 2 (Filtered pulse data):
        ax2.set_title("Filtered")
        ax2.set_xlabel("Time")
        ax2.set_ylabel("Sensor Readings")
        ax2.plot(filtered_dataset_)
        #---Show plot:
        plt.show()

    def main(self):
        #---Normalize data:
        pulse_data_normalized = self.normalize(self.pulse_data)
        #---Perform convolution between the kernel filter and the dataset:
        pulse_data_filtered = pulse_data_normalized
        for i in range(self.number_of_convolutions):
            pulse_data_filtered = self.convolution(pulse_data_filtered, self.kernel)
        #===Plot data:
        #plt.plot(pulse_data_filtered)
        #plt.show()
        #---Count number of pulses:
        self.total_peak_count = self.count_peaks(pulse_data_filtered)
        print(">>> Total number of pulses detected: ", self.total_peak_count)
        #time.sleep(1)  # Sleep for 1 second
        #---plot the pulses before and after the filtering:
        if self.total_peak_count > 0:
            self.plot_pulses(self.pulse_data, pulse_data_filtered)

#=========Run:
#---Custom Convolution filter:
convolution_filter = [-65.625, -30.625, 4.375, 39.375, 74.375, 39.375, 4.375, -65.625]
#===Get file from user:
#---Check if only one argument was given:
if len(sys.argv) == 1:
    print(">>> Please provide the name of the dataset file, or provide it as command-line argument when you run this program again")
    filename = input(">>> Enter dataset filename: ")
#---Check if two arguments were given:
elif len(sys.argv) == 2:
    filename = sys.argv[1]
    print(">>> Given dataset filename: ", filename)
else:
    print(">>> Wrong arguments given!\n")
    print(" Usage: ./PulseCounter <DATASET_FILE> \n")
    sys.exit()

#======Process content of the dataset file:
print(">>> Processing given dataset file ...")
#---Try to open file:
try:
    file_dataset = open(filename, 'r')
except:
    print(">>> Error: Can not open file: ", filename)
    sys.exit()

#---Try to load data into list:
with file_dataset as input_file:
    line_number = 0
    sensor_data = []
    #---Go through each line in the file:
    for line in input_file:
        line_number += 1
        #---Remove any spaces at the start and end of a current line:
        line = line.strip()
        #---Check if current line is empy (i.e. Skip empty lines):
        if line:
            #---Check that all values in the given dataset file are valid whole integers:
            try:
                sensor_data.append(int(line))
            except:
                print(">>> Error: Wrong value in the given dataset was detected at line: ", line_number)
                print("[Only whole integer values are allowed!]")
                sys.exit()

#---Check if enough sensor data was given:
#print("sensor_data >> ", len(sensor_data) )
if (len(sensor_data) < len(convolution_filter)):
    print(">>> Warning: Not enough sensor data samples are given!")
    print("[Minimum of", len(convolution_filter), "samples required]")
    sys.exit()

#======Data Processing:
#---Count number of pulses:
pc = PulseCounter(sensor_data, convolution_filter)
pc.main()


