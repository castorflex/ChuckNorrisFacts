package fr.isima.chuckNorrisFactsV3.entities;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Fact implements Parcelable,Comparable<Fact>{
	
	private int id;
	private String fact;
	private List<String> categories;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFact() {
		return fact;
	}
	public void setFact(String fact) {
		this.fact = fact;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	@Override
	public String toString() {
		return "Fact [id=" + id + ", fact=" + fact + ", author=" + categories + "]";
	}
	public Fact(int id, String fact, List<String> categories) {
		super();
		this.id = id;
		this.fact = fact;
		this.categories = categories;
	}

	
	    ///////////////////////////////////////////////////
		// ////
		// //// PARCEL
		// ////
		// ///////////////////////////////////////////////
		public static final Parcelable.Creator<Fact> CREATOR = new Parcelable.Creator<Fact>() {
			public Fact createFromParcel(Parcel in) {
				return new Fact(in);
			}

			public Fact[] newArray(int size) {
				return new Fact[size];
			}
		};

		public Fact(Parcel in) {
			readFromParcel(in);
		}

		
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}

		
		public void writeToParcel(Parcel parcel, int flags) {
			parcel.writeInt(id);
			parcel.writeString(fact);
			parcel.writeList(categories);
		}

		private void readFromParcel(Parcel parcel) {
			id = parcel.readInt();
			fact = parcel.readString();
			parcel.readList(categories, String.class.getClassLoader());
		}
		
		///////////////////////////////////////////////////////////////////
		
		public int compareTo(Fact otherFact) {
			return (this.getId() > otherFact.getId()) ? 1 : -1;
		}
}





