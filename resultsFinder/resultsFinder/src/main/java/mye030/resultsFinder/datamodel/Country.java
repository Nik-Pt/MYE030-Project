package mye030.resultsFinder.datamodel;

import jakarta.persistence.*;

@Entity
@Table(name = "countries")
public class Country {
		
		@Column(name = "ISO")
		private String ISO;
		
		@Column(name = "ISO3")
		private String ISO3;
		
		@Id
		@Column(name = "ISO_Code")
		private int ISO_Code;
		
		@Column(name = "FIPS")
		private String FIPS;
		
		@Column(name = "Display_Name")
		private String Display_Name;
		
		@Column(name = "Official_Name")
		private String Official_Name;
		
		@Column(name = "Capital")
		private String Capital;
		
		@Column(name = "Continent")
		private String Continent;
		
		@Column(name = "CurrencyCode")
		private String CurrencyCode;
		
		@Column(name = "CurrencyName")
		private String CurrencyName;
		
		@Column(name = "Phone")
		private String Phone;
		
		@Column(name = "Region_Code")
		private float Region_Code;
		
		@Column(name = "Region_Name")
		private String Region_Name;
		
		@Column(name = "Subregion_Code")
		private float Subregion_Code;
		
		@Column(name = "Subregion_Name")
		private String Subregion_Name;
		
		@Column(name = "Intermediate_Region_Code")
		private float Intermediate_Region_Code;
		
		@Column(name = "Intermediate_Region_Name")
		private String Intermediate_Region_Name;
		
		@Column(name = "Status")
		private String Status;
		
		@Column(name = "Developed_or_Developing")
		private String Developed_or_Developing;
		
		@Column(name = "Small_Island_Developing_States")
		private int Small_Island_Developing_States;
		
		@Column(name = "Land_Locked_Developing_Countries")
		private int Land_Locked_Developing_Countries;
		
		@Column(name = "Least_Developed_Countries")
		private int Least_Developed_Countries;
		
		@Column(name = "Area_SqKm")
		private int Area_SqKm;
		
		@Column(name = "Population")
		private int Population;
		
		public Country() {
			
		}

		public Country(String iSO, String iSO3, int iSO_Code, String fIPS, String display_Name, String official_Name,
				String capital, String continent, String currencyCode, String currencyName, String phone,
				float region_Code, String region_Name, float subregion_Code, String subregion_Name,
				float intermediate_Region_Code, String intermediate_Region_Name, String status,
				String developed_or_Developing, int small_Island_Developing_States,
				int land_Locked_Developing_Countries, int least_Developed_Countries, int area_SqKm, int population) {
			super();
			ISO = iSO;
			ISO3 = iSO3;
			ISO_Code = iSO_Code;
			FIPS = fIPS;
			Display_Name = display_Name;
			Official_Name = official_Name;
			Capital = capital;
			Continent = continent;
			CurrencyCode = currencyCode;
			CurrencyName = currencyName;
			Phone = phone;
			Region_Code = region_Code;
			Region_Name = region_Name;
			Subregion_Code = subregion_Code;
			Subregion_Name = subregion_Name;
			Intermediate_Region_Code = intermediate_Region_Code;
			Intermediate_Region_Name = intermediate_Region_Name;
			Status = status;
			Developed_or_Developing = developed_or_Developing;
			Small_Island_Developing_States = small_Island_Developing_States;
			Land_Locked_Developing_Countries = land_Locked_Developing_Countries;
			Least_Developed_Countries = least_Developed_Countries;
			Area_SqKm = area_SqKm;
			Population = population;
		}

		public String getISO() {
			return ISO;
		}

		public void setISO(String iSO) {
			ISO = iSO;
		}

		public String getISO3() {
			return ISO3;
		}

		public void setISO3(String iSO3) {
			ISO3 = iSO3;
		}

		public int getISO_Code() {
			return ISO_Code;
		}

		public void setISO_Code(int iSO_Code) {
			ISO_Code = iSO_Code;
		}

		public String getFIPS() {
			return FIPS;
		}

		public void setFIPS(String fIPS) {
			FIPS = fIPS;
		}

		public String getDisplay_Name() {
			return Display_Name;
		}

		public void setDisplay_Name(String display_Name) {
			Display_Name = display_Name;
		}

		public String getOfficial_Name() {
			return Official_Name;
		}

		public void setOfficial_Name(String official_Name) {
			Official_Name = official_Name;
		}

		public String getCapital() {
			return Capital;
		}

		public void setCapital(String capital) {
			Capital = capital;
		}

		public String getContinent() {
			return Continent;
		}

		public void setContinent(String continent) {
			Continent = continent;
		}

		public String getCurrencyCode() {
			return CurrencyCode;
		}

		public void setCurrencyCode(String currencyCode) {
			CurrencyCode = currencyCode;
		}

		public String getCurrencyName() {
			return CurrencyName;
		}

		public void setCurrencyName(String currencyName) {
			CurrencyName = currencyName;
		}

		public String getPhone() {
			return Phone;
		}

		public void setPhone(String phone) {
			Phone = phone;
		}

		public float getRegion_Code() {
			return Region_Code;
		}

		public void setRegion_Code(float region_Code) {
			Region_Code = region_Code;
		}

		public String getRegion_Name() {
			return Region_Name;
		}

		public void setRegion_Name(String region_Name) {
			Region_Name = region_Name;
		}

		public float getSubregion_Code() {
			return Subregion_Code;
		}

		public void setSubregion_Code(float subregion_Code) {
			Subregion_Code = subregion_Code;
		}

		public String getSubregion_Name() {
			return Subregion_Name;
		}

		public void setSubregion_Name(String subregion_Name) {
			Subregion_Name = subregion_Name;
		}

		public float getIntermediate_Region_Code() {
			return Intermediate_Region_Code;
		}

		public void setIntermediate_Region_Code(float intermediate_Region_Code) {
			Intermediate_Region_Code = intermediate_Region_Code;
		}

		public String getIntermediate_Region_Name() {
			return Intermediate_Region_Name;
		}

		public void setIntermediate_Region_Name(String intermediate_Region_Name) {
			Intermediate_Region_Name = intermediate_Region_Name;
		}

		public String getStatus() {
			return Status;
		}

		public void setStatus(String status) {
			Status = status;
		}

		public String getDeveloped_or_Developing() {
			return Developed_or_Developing;
		}

		public void setDeveloped_or_Developing(String developed_or_Developing) {
			Developed_or_Developing = developed_or_Developing;
		}

		public int getSmall_Island_Developing_States() {
			return Small_Island_Developing_States;
		}

		public void setSmall_Island_Developing_States(int small_Island_Developing_States) {
			Small_Island_Developing_States = small_Island_Developing_States;
		}

		public int getLand_Locked_Developing_Countries() {
			return Land_Locked_Developing_Countries;
		}

		public void setLand_Locked_Developing_Countries(int land_Locked_Developing_Countries) {
			Land_Locked_Developing_Countries = land_Locked_Developing_Countries;
		}

		public int getLeast_Developed_Countries() {
			return Least_Developed_Countries;
		}

		public void setLeast_Developed_Countries(int least_Developed_Countries) {
			Least_Developed_Countries = least_Developed_Countries;
		}

		public int getArea_SqKm() {
			return Area_SqKm;
		}

		public void setArea_SqKm(int area_SqKm) {
			Area_SqKm = area_SqKm;
		}

		public int getPopulation() {
			return Population;
		}

		public void setPopulation(int population) {
			Population = population;
		}

		@Override
		public String toString() {
			return "Country [ISO=" + ISO + ", ISO3=" + ISO3 + ", ISO_Code=" + ISO_Code + ", FIPS=" + FIPS
					+ ", Display_Name=" + Display_Name + ", Official_Name=" + Official_Name + ", Capital=" + Capital
					+ ", Continent=" + Continent + ", CurrencyCode=" + CurrencyCode + ", CurrencyName=" + CurrencyName
					+ ", Phone=" + Phone + ", Region_Code=" + Region_Code + ", Region_Name=" + Region_Name
					+ ", Subregion_Code=" + Subregion_Code + ", Subregion_Name=" + Subregion_Name
					+ ", Intermediate_Region_Code=" + Intermediate_Region_Code + ", Intermediate_Region_Name="
					+ Intermediate_Region_Name + ", Status=" + Status + ", Developed_or_Developing="
					+ Developed_or_Developing + ", Small_Island_Developing_States=" + Small_Island_Developing_States
					+ ", Land_Locked_Developing_Countries=" + Land_Locked_Developing_Countries
					+ ", Least_Developed_Countries=" + Least_Developed_Countries + ", Area_SqKm=" + Area_SqKm
					+ ", Population=" + Population + "]";
		}
		
}
