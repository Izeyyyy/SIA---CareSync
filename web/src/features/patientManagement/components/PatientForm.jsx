import { useState } from "react";

import FormSection from "../../../components/common/FormSection";
import FormField from "../../../components/common/FormField";
import PrimaryButton from "../../../components/common/PrimaryButton";
import SecondaryButton from "../../../components/common/SecondaryButton";

export default function PatientForm({ onSubmit }) {

    const [form, setForm] = useState({

        firstName: "",
        middleInitial: "",
        lastName: "",

        birthDate: "",
        gender: "",

        contactNumber: "",
        address: ""

    });

    const handleChange = (e) => {

        setForm({

            ...form,

            [e.target.name]: e.target.value

        });

    };

    const handleSubmit = (e) => {

        e.preventDefault();

        onSubmit(form);

    };

    return (

        
        <form onSubmit={handleSubmit}>

            {/* We'll add sections next */}
            <FormSection title="Personal Information">

            <FormField label="First Name">

                <input
                    name="firstName"
                    value={form.firstName}
                    onChange={handleChange}
                    required
                />

            </FormField>

            <FormField label="Middle Initial">

                <input
                    name="middleInitial"
                    maxLength="1"
                    value={form.middleInitial}
                    onChange={handleChange}
                />

            </FormField>

            <FormField label="Last Name">

                <input
                    name="lastName"
                    value={form.lastName}
                    onChange={handleChange}
                    required
                />

            </FormField>

            <FormField label="Birth Date">

                <input
                    type="date"
                    name="birthDate"
                    value={form.birthDate}
                    onChange={handleChange}
                    required
                />

            </FormField>

            <FormField label="Gender">

                <select

                    name="gender"

                    value={form.gender}

                    onChange={handleChange}

                    required

                >

                    <option value="">Select Gender</option>

                    <option value="Male">Male</option>

                    <option value="Female">Female</option>

                </select>

            </FormField>

        </FormSection>

        <FormSection title="Contact Information">

            <FormField label="Contact Number">

                <input
                    name="contactNumber"
                    value={form.contactNumber}
                    onChange={handleChange}
                    required
                />

            </FormField>

            <FormField label="Address">

                <textarea

                    rows="4"

                    name="address"

                    value={form.address}

                    onChange={handleChange}

                    required

                />

            </FormField>

        </FormSection>

        <div className="form-actions">

            <SecondaryButton
                type="button"
                onClick={() => window.history.back()}
            >

                Cancel

            </SecondaryButton>

            <PrimaryButton
                type="submit"
            >

                Register Patient

            </PrimaryButton>

        </div>

        </form>

        

    );

}